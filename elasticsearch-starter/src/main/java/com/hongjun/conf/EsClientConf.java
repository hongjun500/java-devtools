package com.hongjun.conf;

import cn.hutool.core.util.ArrayUtil;
import co.elastic.clients.transport.TransportUtils;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.lang.NonNull;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;

@Log4j2
@Configuration
public class EsClientConf extends ElasticsearchConfiguration {

    @NonNull
    @Override
    public ClientConfiguration clientConfiguration() {

        // Es7.x的标头 处理兼容性
        HttpHeaders compatibilityHeaders = new HttpHeaders();
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
                + "compatible-with=7");
        SSLContext sslContext = null;
        try {
            // ca证书
            File file = new ClassPathResource("es/http_ca.crt").getFile();
            sslContext = TransportUtils
                    .sslContextFromHttpCaCrt(file);
        } catch (IOException e) {
            log.info("Elasticsearch的CA证书配置失败");
            throw new RuntimeException(e);
        }
        return ClientConfiguration.builder()
                .connectedTo(ArrayUtil.toArray(Lists.newArrayList("localhost:9200"), String.class))
                // 开启https
//                .usingSsl(sslContext)
                .withBasicAuth("elastic","hongjun500")
                .withDefaultHeaders(compatibilityHeaders)
                .build();
    }


}
