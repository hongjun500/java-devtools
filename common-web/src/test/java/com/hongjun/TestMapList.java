package com.hongjun;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2022/11/11 10:12
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:发生的VS但是
 */
@Log4j2
public class TestMapList {
	@Test
	@Disabled
	void testMap() {
		log.info("---testMap");
		List<Map<String, Object>> objects = Lists.newArrayList();
		for (int j = 0; j < 1; j++) {
			Map<String, Object> objectObjectHashMap = Maps.newHashMap();

			for (int i = 0; i < 3; i++) {
				objectObjectHashMap.put(String.valueOf(i), i + i/1);
				objects.add(objectObjectHashMap);
			}
			objectObjectHashMap.put("123", 32131);
			objects.add(objectObjectHashMap);
		}


		System.out.println(objects);
	}

	@Test
	void testLocalCache() {

	}
}
