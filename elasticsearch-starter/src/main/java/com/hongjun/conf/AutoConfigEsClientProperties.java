package com.hongjun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "common.elasticsearch")
public class AutoConfigEsClientProperties {

    /**
     * 是否开启使用此starter的组件
     */
    private boolean enabled;

    /**
     * 默认为localhost:9200
     */
    private List<String> hostAndPortList = new ArrayList<>(List.of("localhost:9200"));

    /**
     * 基于xPack认证的用户名
     */
    private String authUsername;

    /**
     * 基于xPack认证的密码
     */
    private String authPassword;

    /**
     * 是否开启https
     */
    private boolean hasUsingSsl;

    /**
     * http ca证书的路径
     */
    private String httpCaFilePath;

    /**
     * 是否兼容V7.x
     */
    private boolean compatibilityV7;

    /**
     * 索引名前缀，配置SpEL 表达式使用
     * #{common.elasticsearch.indexNamePrefix}
     */
    private String indexNamePrefix = "";
}
