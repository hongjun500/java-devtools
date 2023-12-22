package com.hongjun.mongodb.crud;

import com.google.common.collect.Lists;
import com.hongjun.mongodb.connection.MongoConnect;
import com.hongjun.response.CommonPage;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.Decimal128;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MongoTMDBMoviesTest {

    private final MongoTMDBMovies mongoTMDBMovies = new MongoTMDBMovies();

    private final MongoConnect mongoConn = new MongoConnect();

    @Test
    void importDocumentFromCsv() {
        try {
            boolean result = mongoTMDBMovies.importDocumentFromCsv();
            assertTrue(result);
        } catch (Exception e) {
            log.error("importDocumentFromCsv error: {}", e.getMessage());
        }
    }

    @Test
    void getById() {
        Document document = mongoTMDBMovies.getById("5");
        log.info("document: {}", document);
    }

    @Test
    void listByTitleOrKeywords() {
        List<Document> documentList = mongoTMDBMovies.listByTitleOrKeywords("Curse of the Golden Flower", "power");
        assertFalse(documentList.isEmpty());
        documentList.forEach(document -> {
            boolean contains = document.getString("title").contains("Curse of the Golden Flower");
            log.info("contains: {}", contains);
            List<Boolean> list = Lists.newArrayList();
            document.getList("keywords", Document.class).forEach(keyword -> {
                list.add(keyword.getString("name").contains("power"));
            });
            assertTrue(contains || list.contains(true));
            list.clear();
        });
        List<Document> documents = mongoTMDBMovies.listByTitleOrKeywords("Dragon", "");
        assertFalse(documents.isEmpty());
        List<Document> list = documents.stream().filter(document ->
                document.getString("original_title").contains("卧虎藏龙")
        ).toList();
        assertFalse(list.isEmpty());

        List<Document> monkeyKing = mongoTMDBMovies.listByTitleOrKeywords("", "monkey king");
        assertFalse(monkeyKing.isEmpty());
        monkeyKing.stream().filter(document ->
                document.getString("original_title").contains("西游记")
        ).forEach(document -> log.info("document: {}", document));
    }

    @Test
    void listAll() {
        List<Document> documents = mongoTMDBMovies.listAll();
        assertEquals(4803, documents.size());
    }

    @Test
    void listPage() {
        CommonPage<Document> page = mongoTMDBMovies.listPage(480, 10);
        assertEquals(481, page.getTotalPage());
    }

    @Test
    void listPageByLanguage() {
        CommonPage<Document> page = mongoTMDBMovies.listPageByLanguage(1, 10, "cn");
        assertEquals(10, page.getData().size());
        CommonPage<Document> page1 = mongoTMDBMovies.listPageByLanguage(2, 10, "cn");
        assertEquals(2, page1.getData().size());

        CommonPage<Document> pageAll = mongoTMDBMovies.listPageByLanguage(2, 10, "");
        assertEquals(4803, pageAll.getTotal());
    }

    @Test
    void listByRuntimeGte() {
        List<Document> documents = mongoTMDBMovies.listByRuntimeGte(120);
        assertFalse(documents.isEmpty());
        documents.forEach(obj -> {
            assertTrue(obj.getInteger("runtime") >= 120);
        });
    }

    @Test
    void listByVoteAverageLte() {
        List<Document> documents = mongoTMDBMovies.listByVoteAverageLte(6);
        assertFalse(documents.isEmpty());
        documents.forEach(obj -> {
            assertTrue(obj.get("vote_average", Decimal128.class).compareTo(Decimal128.parse("6")) <= 0);
        });
    }

    @Test
    void listByRuntimeGteAndVoteAverageLte() {
        mongoTMDBMovies.listByRuntimeGteAndVoteAverageLte(120, 6).forEach(obj -> {
            assertTrue(obj.getInteger("runtime") >= 120);
            assertTrue(obj.get("vote_average", Decimal128.class).compareTo(Decimal128.parse("6")) <= 0);
        });
    }

    @Test
    void listDistinctByField() {
        List<String> keywords = mongoTMDBMovies.listDistinctByField("original_language");
        assertTrue(keywords.contains("zh"));
    }

    @Test
    void listAllIndex() {
        List<Document> documents = mongoTMDBMovies.listAllIndex();
        assertFalse(documents.isEmpty());
        documents.forEach(document -> {
            log.info("document: {}", document);
        });
    }

    @Test
    void createTextIndex() {
        mongoTMDBMovies.createTextIndex("original_title");
    }

    @Test
    void listTextMatchAndOrderYearAsc() {
        List<Document> documents = mongoTMDBMovies.listTextMatchAndOrderYearAsc("滿城盡帶黃金甲");
        assertFalse(documents.isEmpty());
        documents.forEach(document -> {
            assertTrue(document.getString("original_title").contains("黃金甲"));
        });
    }

    @Test
    void dropTextIndex() {
        mongoTMDBMovies.dropTextIndex("original_title");
    }

    @Test
    void dropAllIndex() {
        mongoTMDBMovies.dropAllIndex();
    }

    @Test
    void dropCollection() {
        assertTrue(mongoTMDBMovies.dropCollection());
    }
}