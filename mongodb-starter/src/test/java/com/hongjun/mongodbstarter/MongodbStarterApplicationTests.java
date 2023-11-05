package com.hongjun.mongodbstarter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import com.hongjun.document.TopSpotifySongs;
import com.hongjun.service.TopSpotifySongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MongodbStarterApplicationTests {


	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private TopSpotifySongService topSpotifySongService;


	@Test
	void contextLoads() throws IOException {

		File file = new ClassPathResource("universal_top_spotify_songs.csv").getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		List<Map<String, String>> maps = CsvUtil.getReader().readMapList(reader);

		assert !maps.isEmpty();
		assert maps.get(0).get("name").equals("greedy");
		mongoTemplate.insert(maps, TopSpotifySongs.class);
//		mongoTemplate.insert(maps, "top_spotify_songs");
	}

	@Test
	void queryName() {
		List<TopSpotifySongs> all = topSpotifySongService.listParam("");
		assert all.size() == 62028;
		List<TopSpotifySongs> example = topSpotifySongService.listParam("greedy");
		assert !example.isEmpty();

	}

	@Test
	void del(){
		long count = topSpotifySongService.removeColl("top_spotify_songs");
		assert count == 62028;
	}

	@Test
	void importData(){
//		long count = topSpotifySongService.importData();
//		assert count == 62028;
	}

}
