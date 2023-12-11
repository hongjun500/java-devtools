package com.hongjun.springdata.service;

import com.hongjun.springdata.MongoDBConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@DataMongoTest
@ContextConfiguration(classes = MongoDBConfiguration.class)
class TopSpotifySongServiceImplTest {

	@Autowired
	private TopSpotifySongService topSpotifySongService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@BeforeAll
	static void beforeAll() {
		log.info("beforeAll");
	}
	@BeforeEach
	void setUp() {
		Assertions.assertAll(() -> {
			Assertions.assertNotNull(mongoTemplate);
			Assertions.assertNotNull(topSpotifySongService);
		});
	}

	@Test
	void importData() {
		log.info("importData");
	}

	@Test
	void listParam() {
	}

	@Test
	void removeColl() {
	}
}