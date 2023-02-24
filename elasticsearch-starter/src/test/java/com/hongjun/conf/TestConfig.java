package com.hongjun.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author hongjun500
 * @date 2023/2/24 17:37
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Configuration
@ComponentScan(basePackages = "com.hongjun")
@EnableElasticsearchRepositories
public class TestConfig {
}
