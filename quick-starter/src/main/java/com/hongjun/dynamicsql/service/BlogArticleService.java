package com.hongjun.dynamicsql.service;


import com.hongjun.dynamicsql.model.BlogArticleDO;

import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/4/18
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

public interface BlogArticleService {
	int saveBlog(BlogArticleDO blogArticleDO);
	int saveBlogs(List<BlogArticleDO> blogArticleDOList);


	int delBlogById(Integer id);

	int updateBlog(BlogArticleDO blogArticleDO);

	BlogArticleDO getBlogById(Integer id);

	List<BlogArticleDO> listBlogAll();
	List<Map<String, Object>> listBlogAllToMap();


	List<BlogArticleDO> listBlogByKeyword(String keyword);

	List<Map<String, Object>> listBlogJoinTag(Integer tagId);
}
