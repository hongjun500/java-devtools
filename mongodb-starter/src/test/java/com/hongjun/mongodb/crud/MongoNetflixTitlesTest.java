package com.hongjun.mongodb.crud;

import com.hongjun.mongodb.connection.MongoConn;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MongoNetflixTitlesTest {
	private final MongoNetflixTitles mongoNetflixTitles = new MongoNetflixTitles();
	private final MongoConn mongoConn = new MongoConn();
	private final MongoDatabase db = mongoConn.getDatabase("kaggle");

	@Test
	void importDocumentFromCsv() throws IOException {
		assertNotNull(db);
		boolean importDocumentFromCsv = mongoNetflixTitles.importDocumentFromCsv(mongoConn);
		assertTrue(importDocumentFromCsv);
	}

	@Test
	void pageDocument() {
		assertNotNull(db);
		List<Document> documents = mongoNetflixTitles.pageDocument(881, 10, mongoConn);
		assertNotNull(documents);
		assert documents.size() == 7;
		for (Document document : documents) {
			System.out.println(document.toJson());
		}
	}
}