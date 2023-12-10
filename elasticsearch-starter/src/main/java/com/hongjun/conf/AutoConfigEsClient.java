package com.hongjun.conf;

import co.elastic.clients.transport.TransportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.lang.NonNull;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "common.elasticsearch", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = AutoConfigEsClientProperties.class)
public class AutoConfigEsClient extends ElasticsearchConfiguration {

    @Autowired
    private AutoConfigEsClientProperties autoConfigEsClientProperties;

    @NonNull
    @Override
    public ClientConfiguration clientConfiguration() {
        log.info("自定义封装的common-elasticsearch组件配置初始化");
        ClientConfiguration.ClientConfigurationBuilderWithRequiredEndpoint builder = ClientConfiguration.builder();
        ClientConfiguration.MaybeSecureClientConfigurationBuilder maybeSecureClientConfigurationBuilder =
        builder.connectedTo(autoConfigEsClientProperties.getHostAndPortList().stream().toArray(String[]::new));
        if (autoConfigEsClientProperties.isHasUsingSsl()) {
            SSLContext sslContext;
            try {
                // ca证书验证https
                // File file = new ClassPathResource(autoConfigEsClientProperties.getHttpCaFilePath()).getFile();
                File file = new File(autoConfigEsClientProperties.getHttpCaFilePath());
                sslContext = TransportUtils
                        .sslContextFromHttpCaCrt(file);
            } catch (IOException e) {
                log.error("Elasticsearch CA FILE AUTH FAIL, CA FILE PATH = {}", autoConfigEsClientProperties.getHttpCaFilePath());
                throw new RuntimeException(e);
            }
            maybeSecureClientConfigurationBuilder.usingSsl(sslContext);
        }
        maybeSecureClientConfigurationBuilder.withBasicAuth(autoConfigEsClientProperties.getAuthUsername(), autoConfigEsClientProperties.getAuthPassword());
        if (autoConfigEsClientProperties.isCompatibilityV7()) {
            // Es7.x的标头 处理兼容性
            HttpHeaders compatibilityHeaders = new HttpHeaders();
            compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
            compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
                    + "compatible-with=7");
            maybeSecureClientConfigurationBuilder.withDefaultHeaders(compatibilityHeaders);
        }
        return maybeSecureClientConfigurationBuilder.build();
    }
}
