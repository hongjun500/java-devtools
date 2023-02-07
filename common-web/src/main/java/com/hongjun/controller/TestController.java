package com.hongjun.controller;

import com.hongjun.response.CommonReturnType;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjun500
 * @date 2023/2/6 16:36
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
@RestController
@RequestMapping(value = "/test")
public class TestController {

	@PostMapping(value = "/jsonbody")
	public CommonReturnType<Object> jsonbody(@RequestBody Integer id) {
		int i = 1/0;
		return CommonReturnType.create(id);
	}
}
