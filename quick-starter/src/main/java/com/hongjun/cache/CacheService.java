package com.hongjun.cache;

import com.google.common.collect.Maps;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hongjun500
 * @date 2023/5/4 16:03
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Service
public class CacheService {

	@Autowired
	private RedissonClient redissonClient;

	// @Cacheable(cacheNames = "cache", key = "233")
	public Object getCache() {

		RLock lock = redissonClient.getLock("my-lock");
		Map<Object, Object> map = Maps.newHashMap();
		boolean isLock;
		try {

			/**
			 * 尝试获取锁的最大等待时间是 100 秒，超过这个值还没获取到，就认为获取失败
			 * 锁的持有时间是 10 秒
			 */
			// isLock = lock.tryLock(2, TimeUnit.SECONDS);
			isLock = lock.tryLock(10, 10, TimeUnit.SECONDS);

			if (isLock) {
				//做自己的业务
				Thread.sleep(1000);
				map.put(1, "cache-1");
				map.put(2, "cache-2");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return map;
	}
}
