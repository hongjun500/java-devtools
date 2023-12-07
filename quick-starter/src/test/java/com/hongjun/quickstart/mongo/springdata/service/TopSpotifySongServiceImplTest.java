package com.hongjun.quickstart.mongo.springdata.service;

import com.hongjun.QuickStarterApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = QuickStarterApplication.class)
@RequiredArgsConstructor
class TopSpotifySongServiceImplTest {

	// private final MongoTemplate mongoTemplate;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Test
	void importData() {
		assert mongoTemplate != null;
	}

	@Test
	void listParam() {
	}

	@Test
	void removeColl() {
	}
}