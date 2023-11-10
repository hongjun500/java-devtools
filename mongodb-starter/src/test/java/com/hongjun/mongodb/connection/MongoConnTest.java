package com.hongjun.mongodb.connection;

import org.junit.jupiter.api.Test;

class MongoConnTest {
	private final MongoConn mongoConn = new MongoConn();

	@Test
	void conn() {
		assert mongoConn.getDatabase("kaggle") != null;
	}
}