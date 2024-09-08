package com.hongjun.quickstart.web;

import com.google.common.collect.Maps;
import com.hongjun.response.CommonReturnType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/8/25 16:56
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/restful")
@RequiredArgsConstructor
public class RestfulController{

	private final RedisTemplate<String, Serializable> redisTemplate;

	@GetMapping(value = "/defaultValue")
	public CommonReturnType<Object> defaultValue(@RequestParam(value = "defaultValue", required = false) Integer integer) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Object> map1 = Maps.newHashMap();
		map.put("key", "value");
		map.put("key1", "value1");
		map1.put("key2", map);
		RedissonClient redissonClient = Redisson.create();
		RLock lock = redissonClient.getLock("lock");
		try {
			boolean tryLock = lock.tryLock();
			if (tryLock) {
				log.info("获取到锁");
				Object o = redisTemplate.opsForHash().get("map1", "key2");
				if (o == null) {
					redisTemplate.opsForHash().put("map1", "key2", map1);
				}
			}
		} finally {
			lock.unlock();
		}

		return CommonReturnType.create(integer);
	}
}
