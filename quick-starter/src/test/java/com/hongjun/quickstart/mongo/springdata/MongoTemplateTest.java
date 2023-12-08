package com.hongjun.quickstart.mongo.springdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author hongjun500
 * @date 2023/12/7 18:13
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@DataMongoTest
class MongoTemplateTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	void test() {
		assert mongoTemplate != null;
		// 是否成功连接到 MongoDB
		Assertions.assertNotNull(mongoTemplate.getDb());
	}
}
