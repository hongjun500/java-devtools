package com.hongjun.cache.rest;

import com.hongjun.cache.CacheService;
import com.hongjun.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/cache")
@RestController
public class CacheController {

	@Autowired
	CacheService cacheService;

	@GetMapping(value = "/getCache")
	public CommonReturnType getCache() {
		return CommonReturnType.create(cacheService.getCache());
	}
}


