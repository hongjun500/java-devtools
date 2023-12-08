package com.hongjun.quickstart.mongo.mongodb.crud;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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




	public boolean importDocumentFromCsv(MongoDatabase db) throws IOException {
		File file = new ClassPathResource("mongodb/csv/netflix_titles.csv").getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		List<Map<String, String>> maps = CsvUtil.getReader().readMapList(reader);
		if (maps.isEmpty()) {
			return false;
		}
		List<Document> documents = new ArrayList<>();
		maps.forEach(map -> {
			Document document = new Document();
			document.putAll(map);
			documents.add(document);
		});
		MongoCollection<Document> collection = db.getCollection(COLLECTION);
		InsertManyResult result = collection.insertMany(documents);
		return !result.getInsertedIds().isEmpty();
	}

	public List<Document> listAll(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION);
		FindIterable<Document> documents = collection.find();
		return documents.into(new ArrayList<>());
	}

	public List<Document> listPage(Integer pageNum, Integer pageSize, MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION);
		FindIterable<Document> documents = collection.find();
		// 自从 MongoDB 3.2 版本开始，使用 skip() 方法，允许使用游标方式进行分页。
		documents.skip((pageNum - 1) * pageSize).limit(pageSize);
		return documents.into(new ArrayList<>());
	}

	/**
	 * 根据类型和年份查询文档
	 * @param type Movie/TV Show
	 * @param releaseYear 上映年份
	 * @param db mongo 连接
	 * @return
	 */
	public List<Document> listTypeAndInReleaseYear(String type, List<String> releaseYear, MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION);
		FindIterable<Document> findIterable  = collection.find(Filters.and(Filters.eq("type", type), Filters.in("release_year", releaseYear)));
		return findIterable.into(new ArrayList<>());
	}

	/**
	 * 查询 从 2019 年以后的所有电影
	 * @param type Movie/TV Show
	 * @param releaseYear 上映年份
	 * @param db mongo 连接
	 * @return
	 */
	public List<Document> listMovieGte2019(String type, String releaseYear, MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION);
		FindIterable<Document> findIterable = collection.find(Filters.and(Filters.eq("type", type), Filters.gte("release_year", releaseYear)));
		return findIterable.into(new ArrayList<>());
	}

	/**
	 * 查询关于日本的电视剧
	 * @param type Movie/TV Show
	 * @param listedIn International TV Shows, TV Dramas, TV Thrillers
	 * @param country Japan
	 * @param db mongo 连接
	 * @return
	 */
	public List<Document> listTvShowWithJapan(String type, String listedIn, String country, MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION);
		FindIterable<Document> findIterable = collection.find(Filters.and(Filters.eq("type", type), Filters.or(Filters.regex("country", country), Filters.regex("listed_in", listedIn))));
		return findIterable.into(new ArrayList<>());
	}
}
