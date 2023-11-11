package com.hongjun.mongodb.crud;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import com.hongjun.mongodb.connection.MongoConn;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/11/7 15:30
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: MongoDB example for kaggle with netflix_titles
 */

@Log4j2
public class MongoNetflixTitles {

	public static final String DB = "kaggle";

	public static final String COLLECTION = "netflix_titles";



	public boolean importDocumentFromCsv() throws IOException {
		File file = new ClassPathResource("netflix_titles.csv").getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		List<Map<String, String>> maps = CsvUtil.getReader().readMapList(reader);
		if (maps.isEmpty()) {
			return false;
		}
		MongoConn mongoConn = new MongoConn();
		List<Document> documents = new ArrayList<>();
		maps.forEach(map -> {
			Document document = new Document();
			document.putAll(map);
			documents.add(document);
		});
		MongoCollection<Document> collection = mongoConn.getDatabase(DB).getCollection(COLLECTION);
		InsertManyResult result = collection.insertMany(documents);
		return !result.getInsertedIds().isEmpty();
	}

}
