package com.hongjun.dynamicsql;

import com.google.common.collect.Lists;
import com.hongjun.QuickStarterApplication;
import com.hongjun.dynamicsql.mapper.BlogTagDOMapper;
import com.hongjun.dynamicsql.mapper.support.BlogTagDODynamicSqlSupport;
import com.hongjun.dynamicsql.model.BlogTagDO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.hongjun.dynamicsql.mapper.support.BlogTagDODynamicSqlSupport.blogTagDO;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;


/**
 * @author hongjun500
 * @date 2023/4/18 16:37
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class)
class BlogTagServiceTest {

	@Autowired
	private BlogTagDOMapper blogTagDOMapper;

	List<BlogTagDO> data() {
		BlogTagDO blogTagDO = new BlogTagDO();
		blogTagDO.setName("石乐志");
		blogTagDO.setState(Byte.valueOf("1"));
		BlogTagDO blogTagDO1 = new BlogTagDO();
		blogTagDO1.setName("瓜皮");
		blogTagDO1.setState(Byte.valueOf("1"));
		return Lists.newArrayList(blogTagDO, blogTagDO1);
	}

	@Test
	void testInsert() {
		int resultRow = blogTagDOMapper.insertMultiple(data());
		assert resultRow >= 2;
	}

	@Test
	void testAll() {
		SelectStatementProvider selectStatementProvider = select(blogTagDO.allColumns()).
				from(blogTagDO).
				build().render(RenderingStrategies.MYBATIS3);
		List<BlogTagDO> blogTagDOS = blogTagDOMapper.selectMany(selectStatementProvider);
		blogTagDOS.forEach(obj ->{
			log.info(obj.toString());
		});
		assert blogTagDOS.size() >= 2;
	}
}
