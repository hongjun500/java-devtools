package com.hongjun.index;

import com.hongjun.BookRepository;
import com.hongjun.conf.TestConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author hongjun500
 * @date 2023/2/24 13:47
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@SpringBootTest(classes = TestConfig.class)
@Log4j2
public class BookServiceTest {


	@Autowired
	BookRepository bookRepository;


	@Test
	public void testBook() {
		Assertions.assertNotNull(bookRepository);
	}
}
