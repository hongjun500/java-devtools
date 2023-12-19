package com.hongjun.mongodb.springdata;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author hongjun500
 * @date 2023/12/10 13:26
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: 依赖自动配置
 */

@SpringBootApplication
// 启用 MongoDB 审计功能 @CreatedBy、@LastModifiedBy、@CreatedDate、@LastModifiedDate
@EnableMongoAuditing
class MongoDBConfiguration {
}
