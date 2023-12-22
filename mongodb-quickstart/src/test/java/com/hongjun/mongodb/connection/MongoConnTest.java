package com.hongjun.mongodb.connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MongoConnTest {
	private final MongoConnect mongoConn = new MongoConnect();

	@Test
	void conn() {
		assertNotNull(mongoConn.getDatabase("mongodb"));
	}
}