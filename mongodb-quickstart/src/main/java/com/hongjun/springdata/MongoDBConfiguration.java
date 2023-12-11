package com.hongjun.springdata;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author hongjun500
 * @date 2023/12/10 13:26
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: MongoDBAutoConfiguration
 */

@ComponentScan(basePackages = "com.hongjun.springdata")
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.hongjun.*.repositories")
public class MongoDBConfiguration {
}
