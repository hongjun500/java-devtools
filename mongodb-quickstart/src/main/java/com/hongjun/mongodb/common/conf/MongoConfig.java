package com.hongjun.mongodb.common.conf;

import com.hongjun.mongodb.common.CommonRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author hongjun500
 * @date 2025/3/19 12:13
 * @tool Mac mini
 * Created with 2024.1.7.IntelliJ IDEA
 * Description:
 */
@Configuration
@EnableMongoRepositories(
        basePackages = {"com.hongjun.mongodb"},
        repositoryBaseClass = CommonRepositoryImpl.class
)
public class MongoConfig {
}
