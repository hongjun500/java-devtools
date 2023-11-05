package com.hongjun.web;

import com.hongjun.response.CommonReturnType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjun500
 * @date 2023/8/25 16:56
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@RestController
@RequestMapping(value = "/restful")
public class RestfulController{

	@GetMapping(value = "/defaultValue")
	public CommonReturnType<Object> defaultValue(@RequestParam(value = "defaultValue", required = false) Integer integer) {
		return CommonReturnType.create(integer);
	}
}
