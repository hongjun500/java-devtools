package com.hongjun.mongodb.crud;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.hongjun.mongodb.connection.MongoConnect;
import com.hongjun.mongodb.util.FileResourcesUtil;
import com.hongjun.response.CommonPage;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import com.mongodb.client.result.InsertManyResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Decimal128;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
@Slf4j
public class MongoTMDBMovies {

    public static final String DB_NAME = "kaggle";

    public static final String COLLECTION_NAME = "tmdb_movies";

    public static final MongoConnect mongoConn = new MongoConnect();

    public static final MongoCollection<Document> collection = mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME);

    public static final MongoCollection<Document> collection = mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME);


    public boolean importDocumentFromCsv() throws IOException {
        List<Map<String, String>> maps = FileResourcesUtil.readCSV("mongo/csv/tmdb_5000_movies.csv");
        if (maps.isEmpty()) {
            return false;
        }
        List<Document> documents = Lists.newArrayListWithExpectedSize(maps.size());
        maps.forEach(objMap -> {
            Document document = new Document();
            document.putAll(objMap);
            // 覆盖其它字段
            document.put("popularity", Decimal128.parse(objMap.get("popularity")));
            document.put("release_date", parseLocalDate(objMap, "release_date"));
            document.put("revenue", Long.parseLong(objMap.get("revenue")));
            // 有些数据会被读取成 "xx.0"
            document.put("runtime", MapUtil.get(objMap, "runtime", Integer.class));
            document.put("vote_average", Decimal128.parse(objMap.get("vote_average")));
            document.put("vote_count", Integer.parseInt(objMap.get("vote_count")));
            documents.add(document);
        });
        InsertManyResult insertManyResult = mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME).insertMany(documents);
        return insertManyResult.wasAcknowledged();
    }

    private LocalDate parseLocalDate(Map<String, String> map, String key) {
        String value = map.get(key);
        return StrUtil.isBlank(value) ? LocalDate.now() : LocalDateTimeUtil.parseDate(value, "yyyy/M/d");
    }

    public Document getById(String _id) {
        return collection.find(Filters.eq("_id", _id)).first();
    }

    public List<Document> listAll() {
        return mongoConn.getDatabase(DB_NAME).getCollection(COLLECTION_NAME).find().into(Lists.newArrayList());
    }

    public CommonPage<Document> listPage(Integer pageNum, Integer pageSize) {

        long count = collection.countDocuments();
        List<Document> documents = collection.find().skip(pageNum > 0 ? ((pageNum - 1) * pageSize) : 0).limit(pageSize).into(Lists.newArrayList());
        return CommonPage.create(documents, pageNum, pageSize, count);
    }

    public CommonPage<Document> listPageByLanguage(Integer pageNum, Integer pageSize, String language) {
        Bson filter = new Document();
        if (StrUtil.isNotBlank(language)) {
            filter = Filters.regex("original_language", language);
        }
        long count = collection.countDocuments(filter);
        List<Document> documents = collection.find(filter).skip(pageNum > 0 ? ((pageNum - 1) * pageSize) : 0).limit(pageSize).into(Lists.newArrayList());
        return CommonPage.create(documents, pageNum, pageSize, count);
    }

    public List<Document> listByTitleOrKeywords(String title, String keywords) {
        Bson filter = new Document();
        List<Bson> filters = Lists.newArrayList();
        if (StrUtil.isNotBlank(title)) {
            filters.add(Filters.regex("title", title));
        }
        if (StrUtil.isNotBlank(keywords)) {
            filters.add(Filters.regex("keywords.name", keywords));
        }
        if (!filters.isEmpty()) {
            filter = Filters.or(filters);
        }
        return collection.find(filter).into(Lists.newArrayList());
    }

    public List<Document> listByRuntimeGte(Integer runtimeGte) {
        Bson filter = new Document();
        if (runtimeGte != null) {
            filter = Filters.gte("runtime", runtimeGte);
        }
        return collection.find(filter).into(Lists.newArrayList());
    }

    public List<Document> listByVoteAverageLte(Object voteAverageLte) {
        Bson filter = new Document();
        if (voteAverageLte != null) {
            filter = Filters.lte("vote_average", Decimal128.parse(voteAverageLte.toString()));
        }
        return collection.find(filter).into(Lists.newArrayList());
    }

    public List<Document> listByRuntimeGteAndVoteAverageLte(Integer runtimeGte, Object voteAverageLte) {
        Bson filter = new Document();
        List<Bson> filters = Lists.newArrayList();
        if (runtimeGte != null) {
            filters.add(Filters.gte("runtime", runtimeGte));
        }
        if (voteAverageLte != null) {
            filters.add(Filters.lte("vote_average", Decimal128.parse(voteAverageLte.toString())));
        }
        if (!filters.isEmpty()) {
            filter = Filters.and(filters);
        }
        return collection.find(filter).into(Lists.newArrayList());
    }

    public List<String> listDistinctByField(String field) {
        return collection.distinct(field, String.class).into(Lists.newArrayList());
    }

    public List<Document> listTextMatchAndOrderYearAsc(String text) {
        Bson filter = new Document();
        if (StrUtil.isNotBlank(text)) {
            TextSearchOptions options = new TextSearchOptions().caseSensitive(true);
            filter = Filters.text((text), options);
        }
        Bson sort = Sorts.ascending("release_date");
        return collection.find(filter).sort(sort).into(Lists.newArrayList());
    }

    public boolean dropCollection() {
        collection.drop();
        // 判断是否删除成功
        return collection.countDocuments() <= 0;
    }

    public List<Document> listAllIndex() {
        return collection.listIndexes().into(new ArrayList<>());
    }

    public void createTextIndex(String field) {
        Bson keys = Indexes.text(field);
        // 设置为zh
        IndexOptions indexOptions = new IndexOptions();
        indexOptions.languageOverride("zh");
        String index = collection.createIndex(keys, indexOptions);
        log.info("createIndex: {}", index);
    }

    public void dropTextIndex(String field) {
        collection.listIndexes().forEach(document -> {
            if (document.getString("name").contains(field)) {
                log.info("dropIndex: {}", document.get("name"));
                collection.dropIndex(document.get("name").toString());
            }
        });
    }

    public void dropAllIndex() {
        collection.dropIndexes();
    }
}
