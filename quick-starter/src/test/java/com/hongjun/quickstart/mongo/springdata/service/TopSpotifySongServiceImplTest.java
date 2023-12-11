package com.hongjun.quickstart.mongo.springdata.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import com.hongjun.QuickStarterApplication;
import com.hongjun.quickstart.mongo.springdata.document.TopSpotifySongs;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = QuickStarterApplication.class)
class TopSpotifySongServiceImplTest {

	@Autowired
	private TopSpotifySongService topSpotifySongService;

	private static List<Map<String, String>> maps;

	@BeforeAll
	static void beforeAll() throws IOException {

		File file = new ClassPathResource("mongodb/csv/universal_top_spotify_songs.csv").getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		maps = CsvUtil.getReader().readMapList(reader);
		assert !maps.isEmpty();
	}


	@Test
	void importData() {
		topSpotifySongService.importData(maps, TopSpotifySongs.class);
	}

	@Test
	void listParam() {
		List<TopSpotifySongs> ganglands = topSpotifySongService.listParam("Ganglands");
		assert !ganglands.isEmpty();
	}

	@Test
	void removeColl() {
		long topSpotifySongs = topSpotifySongService.removeColl("top_spotify_songs");
		assert topSpotifySongs == 62028;
	}

	@AfterAll
	static void afterAll() {
		maps = new ArrayList<>();
	}
}