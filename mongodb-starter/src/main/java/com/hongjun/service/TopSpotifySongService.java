package com.hongjun.service;

import com.hongjun.data.TopSpotifySongs;

import java.util.List;
import java.util.Map;

public interface TopSpotifySongService {
	void importData(List<Map<String, String>> maps);

	List<TopSpotifySongs> listParam(String name);
}
