package com.hongjun.index;

import com.hongjun.AppMain;
import com.hongjun.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;

/**
 * @author hongjun500
 * @date 2023/2/24 13:47
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@SpringBootTest(classes = AppMain.class)
@Log4j2
public class BookServiceTest {


	@Autowired
	BookRepository bookRepository;

	@Autowired
	ElasticsearchOperations elasticsearchOperations;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;


	@Test
	public void testBook() {
		String sr = "eyJ2ZXIiOiI4LjUuMyIsImFkciI6WyIxMC4xLjE2LjU0OjkyMDAiXSwiZmdyIjoiZjNkYzM4YTI5Y2ExOTRiMTEzODNkZjg1MDA1OGQ1ZTdlMDI1ZDgyNjZiMTU5NTJiYThmYTIwYjVjMTU0ZWViNyIsImtleSI6IjVoYThwb1lCTGRYblB2SXozdThKOklNR2h0SndyVDVhQ3FSMlhBQjhqM2cifQ==";
		Assertions.assertNotNull(bookRepository);
	}

	@Test
	void testInitIndex() {
		// elasticsearchOperations.indexOps(Book.class).delete();
		IndexOperations indexOperations = elasticsearchTemplate.indexOps(Book.class);

		if (!indexOperations.exists()) {
			indexOperations.create();
			// Document mapping = indexOperations.createMapping(Book.class);
			// indexOperations.putMapping(mapping);
			indexOperations.putMapping();
			return;
		}
		indexOperations.putMapping();
	}

	@Test
	void testGetIndex() {
		IndexOperations indexOperations = elasticsearchTemplate.indexOps(Book.class);

	}
}
