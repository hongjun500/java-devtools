package com.hongjun.mongodb.crud;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.hongjun.mongodb.connection.MongoConn;
import com.hongjun.mongodb.util.FileResourcesUtil;
import com.hongjun.response.CommonPage;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;
import lombok.Data;
import org.bson.Document;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/12/18 16:15
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
public class MongoTMDBMovies {

    public static final String DB_NAME = "kaggle";

    public static final String COLLECTION_NAME = "tmdb_movies";

    public static final MongoConn mongoConn = new MongoConn();


    public boolean importDocumentFromCsv() throws IOException {
        List<Map<String, String>> maps = FileResourcesUtil.readCSV("mongo/csv/tmdb_movies.csv");
        if (maps.isEmpty()) {
            return false;
        }
        List<Document> documents = Lists.newArrayListWithExpectedSize(maps.size());
        maps.forEach(objMap -> {
            Document document = new Document();
            document.putAll(objMap);
            // 覆盖其它字段
            document.put("release_date", parseLocalDate(objMap, "release_date"));
            document.put("revenue", Long.parseLong(objMap.get("revenue")));
            document.put("runtime", Integer.parseInt("".equals(objMap.get("runtime")) ? "0" : objMap.get("runtime")));
            document.put("vote_average", BigDecimal.valueOf(Double.parseDouble(objMap.get("vote_average"))));
            document.put("vote_count", Integer.parseInt(objMap.get("vote_count")));
            documents.add(document);
        });
        InsertManyResult insertManyResult = mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME).insertMany(documents);
        return insertManyResult.wasAcknowledged();
    }

    private LocalDate parseLocalDate(Map<String, String> map, String key) {
        String value = map.get(key);
        return StrUtil.isBlank(value) ? LocalDate.now() : LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public List<Document> listAll() {
        return mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME).find().into(Lists.newArrayList());
    }

    public CommonPage<Document> listPage(Integer pageNum, Integer pageSize) {
        MongoCollection<Document> collection = mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME);
        long count = collection.countDocuments();
        List<Document> documents = collection.find().skip(pageNum * pageSize).limit(pageSize).into(Lists.newArrayList());
        return CommonPage.create(documents, pageNum, pageSize, count);
    }
}
