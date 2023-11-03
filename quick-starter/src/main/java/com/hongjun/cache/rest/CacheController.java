package com.hongjun.cache.rest;

import com.hongjun.cache.CacheService;
import com.hongjun.response.CommonReturnType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjun500
 * @date 2023/5/4 15:28
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Tag(name = "缓存控制器", description = "测试redisson锁")
@RequestMapping(value = "/cache")
@RestController
public class CacheController {

	private final CacheService cacheService;

	public CacheController(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@GetMapping(value = "/getCache")
	@Operation(summary = "获取缓存数据")
	public CommonReturnType<Object> getCache() {
		return CommonReturnType.create(cacheService.getCache());
	}

}


