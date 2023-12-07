package com.hongjun.quickstart.mongo.springdata.service;

import com.hongjun.quickstart.mongo.springdata.document.TopSpotifySongs;

import java.util.List;
import java.util.Map;

public interface TopSpotifySongService {
	void importData(List<Map<String, String>> maps, Class clazz);

	List<TopSpotifySongs> listParam(String name);

	long removeColl(String collName);
}
