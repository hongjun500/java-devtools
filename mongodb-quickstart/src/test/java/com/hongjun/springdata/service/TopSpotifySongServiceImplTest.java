package com.hongjun.springdata.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import com.hongjun.springdata.MongoDBConfiguration;
import com.hongjun.springdata.document.TopSpotifySongs;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@DataMongoTest
@ContextConfiguration(classes = MongoDBConfiguration.class)
class TopSpotifySongServiceImplTest {

	private static List<Map<String, String>> maps;

	@Autowired
	private TopSpotifySongService topSpotifySongService;

	@Autowired
	private MongoTemplate mongoTemplate;



	@BeforeAll
	static void beforeAll() throws IOException {
		// 获取 csv 文件
		File file = new ClassPathResource("mongo/csv/universal_top_spotify_songs.csv").getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		maps = CsvUtil.getReader().readMapList(reader);
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
		topSpotifySongService.importData(maps, TopSpotifySongs.class);
		List<TopSpotifySongs> topSpotifySongs = topSpotifySongService.listParam("");
		Assertions.assertEquals(62028, topSpotifySongs.size());
	}

	@Test
	void listParam() {
		log.info("listParam");
		List<TopSpotifySongs> topSpotifySongs = topSpotifySongService.listParam("wayward one");
		Assertions.assertEquals(0, topSpotifySongs.size());
	}

	@Test
	void removeColl() {
		log.info("removeColl");
		long top_spotify_songs = topSpotifySongService.removeColl("top_spotify_songs");
		Assertions.assertEquals(62028, top_spotify_songs);
	}

}