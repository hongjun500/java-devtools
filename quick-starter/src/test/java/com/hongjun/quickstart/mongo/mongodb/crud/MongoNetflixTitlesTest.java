package com.hongjun.quickstart.mongo.mongodb.crud;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Maps;
import com.hongjun.quickstart.mongo.mongodb.connection.MongoConn;
import com.hongjun.response.CommonPage;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class MongoNetflixTitlesTest {
	private final MongoNetflixTitles mongoNetflixTitles = new MongoNetflixTitles();
	private final MongoConn mongoConn = new MongoConn();
	private final MongoDatabase db = mongoConn.getDatabase("kaggle");

	@Test
	void importDocumentFromCsv() throws IOException {
		assertNotNull(db);
		boolean importDocumentFromCsv = mongoNetflixTitles.importDocumentFromCsv(db);
		assertTrue(importDocumentFromCsv);
	}

	@Test
	void listAll() {
		assertNotNull(db);
		List<Document> documents = mongoNetflixTitles.listAll(db);
		assertNotNull(documents);
		assert documents.size() == 8807;
		CommonPage.paginate(documents, 1, 10).getData().forEach(document ->
				log.info(document.toJson())
		);
	}

	@Test
	void pageDocument() {
		assertNotNull(db);
		List<Document> documents = mongoNetflixTitles.listPage(881, 10, db);
		assertNotNull(documents);
		assert documents.size() == 7;
		for (Document document : documents) {
			log.info(document.toJson());
		}
	}

	@Test
	void listTypeAndInReleaseYear() {
		assertNotNull(db);
		List<Document> documents = mongoNetflixTitles.listTypeAndInReleaseYear("Movie", Lists.newArrayList("2019","2020"), db);
		assertNotNull(documents);
		assert documents.size() == 1150;
		Map<String, List<Document>> map = Maps.newHashMapWithExpectedSize(2);
		documents.forEach(document ->{
			String json = document.toJson();
			JSONObject jsonObject = CommonFastJsonUtil.toJsonObject(json);
			String releaseYear = jsonObject.getString("release_year");
			if (map.containsKey(releaseYear)){
				map.get(releaseYear).add(document);
			}else {
				List<Document> list = Lists.newArrayList(document);
				map.put(releaseYear, list);
			}
		});
		Set<String> keySet = map.keySet();
		assert keySet.size() == 2;
		keySet.forEach(key ->{
			log.info("key: {}", key);
			List<Document> list = map.get(key);
			list.forEach(document -> log.info(document.toJson()));
		});
	}

	@Test
	void listMovieGte2019(){
		assertNotNull(db);
		List<Document> documents = mongoNetflixTitles.listMovieGte2019("Movie", "2019", db);
		assertNotNull(documents);

		documents.forEach(document ->{
			String json = document.toJson();
			JSONObject jsonObject = CommonFastJsonUtil.toJsonObject(json);
			String releaseYear = jsonObject.getString("release_year");
			assert Integer.parseInt(releaseYear) >= 2019;
		});
	}

	@Test
	void listTvShowWithJapan(){
		assertNotNull(db);
		List<Document> documents = mongoNetflixTitles.listTvShowWithJapan("TV Show", "International TV Shows, TV Dramas, TV Thrillers", "Japan", db);
		assertNotNull(documents);
	}
}