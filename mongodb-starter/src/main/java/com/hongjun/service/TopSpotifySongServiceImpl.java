package com.hongjun.service;

import com.google.common.collect.Lists;
import com.hongjun.data.TopSpotifySongs;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/11/3 17:16
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

@Service
public class TopSpotifySongServiceImpl implements TopSpotifySongService {

	private final MongoTemplate mongoTemplate;

	public TopSpotifySongServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void importData(List<Map<String, String>> maps) {
		if (maps.isEmpty()) {
			return;
		}
		List<TopSpotifySongs> list = Lists.newArrayListWithExpectedSize(maps.size());
		maps.forEach(map ->{
			TopSpotifySongs topSpotifySongs = new TopSpotifySongs();
			map.forEach((key, value) -> {
				// TODO: 2023/11/3 转换字段

			});
		});
		mongoTemplate.insert(maps, "top_spotify_songs");
	}

	@Override
	public List<TopSpotifySongs> listParam(String name) {

		return null;
	}

}
