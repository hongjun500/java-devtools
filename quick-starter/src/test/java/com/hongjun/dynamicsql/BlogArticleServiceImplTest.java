package com.hongjun.dynamicsql;

import com.google.common.collect.Lists;
import com.hongjun.QuickStarterApplication;
import com.hongjun.dynamicsql.model.BlogArticleDO;
import com.hongjun.dynamicsql.service.BlogArticleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class)
class BlogArticleServiceImplTest {

	@Autowired
	private BlogArticleService blogArticleService;

	List<BlogArticleDO> data() {
		BlogArticleDO blogArticleDO = new BlogArticleDO();
		blogArticleDO.setContent("刁德一李在赣神魔");
		blogArticleDO.setTitle("刁德一");
		BlogArticleDO blogArticleDO1 = new BlogArticleDO();
		blogArticleDO1.setContent("刁德二李在淦神魔");
		blogArticleDO1.setTitle("刁德二");
		BlogArticleDO blogArticleDO2 = new BlogArticleDO();
		blogArticleDO2.setContent("刁德三李崽赣神魔");
		blogArticleDO2.setTitle("刁德三");
		BlogArticleDO blogArticleDO3 = new BlogArticleDO();
		blogArticleDO3.setContent("刁德日李崽赣肾魔");
		blogArticleDO3.setTitle("刁德日");
		return Lists.newArrayList(blogArticleDO, blogArticleDO1, blogArticleDO2, blogArticleDO3);
	}

	@Test
	void saveBlog() {
		List<BlogArticleDO> collect = data().stream().filter(obj -> obj.getTitle().equals("刁德日")).toList();
		int resultRow = blogArticleService.saveBlog(collect.get(0));
		assert resultRow >= 1;
	}

	@Test
	void saveBlogs() {
		int resultRow = blogArticleService.saveBlogs(data());
		assert resultRow >= 4;
	}

	@Test
	void delBlogById() {

	}

	@Test
	void updateBlog() {
		BlogArticleDO blogArticleDO = new BlogArticleDO();
		blogArticleDO.setId(1);
		blogArticleDO.setTagId(1);
		int resultRow = blogArticleService.updateBlog(blogArticleDO);
		assert resultRow == 1;
	}

	@Test
	void getBlogById() {
		BlogArticleDO blogArticleDO = blogArticleService.getBlogById(1);
		assert blogArticleDO != null;
		log.info(blogArticleDO.toString());
	}

	@Test
	void listBlogAll() {
		List<BlogArticleDO> blogArticleDOS = blogArticleService.listBlogAll();
		blogArticleDOS.forEach(log::info);
	}

	@Test
	void listBlogAllToMap() {
		List<Map<String, Object>> maps = blogArticleService.listBlogAllToMap();
		maps.forEach(obj -> {
			log.info(obj.get("id"));
		});
		maps.forEach(log::info);
	}

	@Test
	void listBlogByKeyword() {
		List<BlogArticleDO> blogArticleDOS = blogArticleService.listBlogByKeyword("刁德一");
		assert blogArticleDOS.size() == 1;
		blogArticleDOS.forEach(log::info);
	}


	@Test
	void listBloJoinToMap() {
		List<Map<String, Object>> maps = blogArticleService.listBlogJoinTag(1);
		maps.forEach(obj -> {
			log.info(obj.get("id"));
		});
		maps.forEach(log::info);
	}

}