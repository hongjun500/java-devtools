package com.hongjun.dynamicsql.controller;

import com.hongjun.dynamicsql.service.BlogArticleService;
import com.hongjun.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjun500
 * @date 2023/4/26 11:18
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@RequestMapping(value = "/blog")
@RestController
public class BlogController {
	@Autowired
	private BlogArticleService blogArticleService;


	@PostMapping(value = "/article")
	public CommonReturnType<Object> article(Integer blogDTO) {
		blogArticleService.saveBlog(null);
		return CommonReturnType.create(blogDTO);
	}
}
