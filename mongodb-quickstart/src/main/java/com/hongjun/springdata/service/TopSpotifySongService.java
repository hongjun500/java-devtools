package com.hongjun.springdata.service;

import com.hongjun.springdata.document.TopSpotifySongs;

import java.util.List;
import java.util.Map;

public interface TopSpotifySongService {
	void importData(List<Map<String, String>> maps, Class clazz);

	/**
	 * 根据歌曲名查询
	 * @param name 歌曲名, 可以为空则查询所有
	 * @return
	 */
	List<TopSpotifySongs> listParam(String name);

	long removeColl(String collName);
}
