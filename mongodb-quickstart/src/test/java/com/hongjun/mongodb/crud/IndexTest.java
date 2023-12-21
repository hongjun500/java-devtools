package com.hongjun.mongodb.crud;

import com.hongjun.mongodb.connection.MongoConn;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author hongjun500
 * @date 2023/12/20 17:47
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */

@Slf4j
class IndexTest {
    private final MongoConn mongoConn = new MongoConn();
    private MongoCollection<Document> mongoCollection;

    @BeforeEach
    public void setUp() {
        mongoCollection = mongoConn.getDatabase("kaggle").getCollection("blog");
    }

    @Test
    void listIndexes() {
        List<Document> documents = mongoCollection.listIndexes().into(Lists.newArrayList());
        documents.forEach(index ->
           log.info("index: {}", index)
        );
    }
}
