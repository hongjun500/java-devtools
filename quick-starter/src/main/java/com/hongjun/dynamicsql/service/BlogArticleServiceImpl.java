package com.hongjun.dynamicsql.service;

import com.google.common.collect.Lists;
import com.hongjun.dynamicsql.mapper.BlogArticleDOMapper;
import com.hongjun.dynamicsql.model.BlogArticleDO;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hongjun.dynamicsql.mapper.support.BlogArticleDODynamicSqlSupport.*;
import static com.hongjun.dynamicsql.mapper.support.BlogTagDODynamicSqlSupport.blogTagDO;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author hongjun500
 * @date 2023/4/18 10:44
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Service
public class BlogArticleServiceImpl implements BlogArticleService {
	@Autowired
	private BlogArticleDOMapper blogArticleDOMapper;


	@Override
	public int saveBlog(BlogArticleDO blogArticleDO) {
		return blogArticleDOMapper.insertSelective(blogArticleDO);
	}

	@Override
	public int saveBlogs(List<BlogArticleDO> blogArticleDOList) {
		return blogArticleDOMapper.insertMultiple(blogArticleDOList);
	}

	@Override
	public int delBlogById(Integer id) {
		return blogArticleDOMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateBlog(BlogArticleDO blogArticleDO) {
		return blogArticleDOMapper.updateByPrimaryKeySelective(blogArticleDO);
	}

	@Override
	public BlogArticleDO getBlogById(Integer id) {
		Optional<BlogArticleDO> articleDOOptional = blogArticleDOMapper.selectByPrimaryKey(id);
		return articleDOOptional.orElse(null);
	}

	@Override
	public List<BlogArticleDO> listBlogAll() {
		// 查询所有列字段
		SelectStatementProvider selectStatementProvider = select(blogArticleDO.allColumns()).
				from(blogArticleDO)
				.build().render(RenderingStrategies.MYBATIS3);
		return blogArticleDOMapper.selectMany(selectStatementProvider);
	}

	@Override
	public List<Map<String, Object>> listBlogAllToMap() {
		SelectStatementProvider selectStatementProvider =
				// 查询所有列字段
				select(blogArticleDO.allColumns()).
				from(blogArticleDO).
				build().render(RenderingStrategies.MYBATIS3);
		return blogArticleDOMapper.selectManyToMap(selectStatementProvider);
	}

	@Override
	public List<BlogArticleDO> listBlogByKeyword(String keyword) {
		SelectStatementProvider selectStatementProvider = select(content, id, title).
				from(blogArticleDO).
				where(content, isLike(keyword + "%")).
				build().render(RenderingStrategies.MYBATIS3);
		return blogArticleDOMapper.selectMany(selectStatementProvider);
	}

	@Override
	public List<Map<String, Object>> listBlogJoinTag(Integer tagId) {
		SelectStatementProvider selectStatementProvider = select(Lists.newArrayList(blogArticleDO.allColumns(), blogTagDO.allColumns())).
				from(blogArticleDO, "article").
				leftJoin(blogTagDO, "tag").
				on(blogArticleDO.tagId, equalTo(blogTagDO.id)).
				build().render(RenderingStrategies.MYBATIS3);
		return blogArticleDOMapper.selectManyToMap(selectStatementProvider);
	}
}
