package com.hongjun.springdata.service;

import com.hongjun.springdata.document.TopSpotifySongs;

import java.util.List;
import java.util.Map;

public interface TopSpotifySongService {
	void importData(List<Map<String, String>> maps);

	List<TopSpotifySongs> listParam(String name);

	long removeColl(String collName);
}
