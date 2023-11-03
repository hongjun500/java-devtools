package com.hongjun.mongodbstarter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MongodbStarterApplicationTests {

	@Test
	void contextLoads() throws IOException {

		File file = new ClassPathResource("universal_top_spotify_songs.csv").getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		List<Map<String, String>> maps = CsvUtil.getReader().readMapList(reader);

		assert !maps.isEmpty();
		assert maps.get(0).get("name").equals("greedy");
	}

}
