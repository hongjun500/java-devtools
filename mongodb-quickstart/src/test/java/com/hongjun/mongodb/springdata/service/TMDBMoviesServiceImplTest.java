package com.hongjun.mongodb.springdata.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hongjun.mongodb.springdata.document.TMDBMovies;
import com.hongjun.mongodb.util.FileResourcesUtil;
import com.hongjun.response.CommonPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;


// @ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = TMDBMoviesServiceImpl.class)


//  @DataMongoTest 只配置一个 MongoTemplate，并且扫描 @Document 注解的实体类，以及配置了 Spring Data MongoDB 的 Repository 接口
// 常规的 @Component 和 @ConfigurationProperties 不会被扫描。

// 没有 @SpringBootApplication 注解时单此注解即可注入 TMDBMoviesServiceImpl
// @SpringJUnitConfig(TMDBMoviesServiceImpl.class)
@DataMongoTest
@Import(TMDBMoviesServiceImpl.class)
class TMDBMoviesServiceImplTest {
    private static List<Map<String, String>> maps;

    private static Cache<String, List<Map<String, String>>> cache = CacheBuilder.newBuilder().build();

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    TMDBMoviesService tmdbMoviesService;


    @BeforeEach
    void setUp() throws ExecutionException {
        assertNotNull(applicationContext);
        assertNotNull(tmdbMoviesService);
        assertNotNull(mongoTemplate);
        // if (maps == null) {
        maps = cache.get("maps", () -> FileResourcesUtil.readCSV("mongo/csv/tmdb_5000_movies.csv"));
        // }
    }

    @AfterEach
    void tearDown() {
        // maps = null;
    }

    @Test
    void contextLoads() {
        tmdbMoviesService.contextLoads();
    }

    @Test
    void convertToTMDBMoviesAndSave() {
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.convertToTMDBMovies(maps);
        assertEquals(4803, tmdbMovies.size());

        boolean saved = tmdbMoviesService.saveAll(tmdbMovies);
        assertTrue(saved);
    }

    @Test
    void getById() {
        TMDBMovies tmdbMovies = tmdbMoviesService.getById("5");
        assertNotNull(tmdbMovies);
        System.out.println(tmdbMovies);
        assertEquals("5", tmdbMovies.getId());
        assertEquals("Four Rooms", tmdbMovies.getTitle());
    }

    @Test
    void listByPage() {
        CommonPage<TMDBMovies> commonPage = tmdbMoviesService.listByPage(0, 10);
        assertEquals(481, commonPage.getTotalPage());
    }

    @Test
    void listByTitleOrKeywords() {
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.listByTitleOrKeywords("Jarhead", "hotel");
        assertEquals(54, tmdbMovies.size());
    }

    @Test
    void listByRuntimeGte() {
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.listByRuntimeGte(120);
        assertEquals(1073, tmdbMovies.size());
    }

    @Test
    void listByVoteAverageLte() {
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.listByVoteAverageLte(6);
        assertEquals(2047, tmdbMovies.size());
    }

    @Test
    void listByRuntimeGteAndVoteAverageLte() {
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.listByRuntimeGteAndVoteAverageLte(120, 6);
        assertEquals(196, tmdbMovies.size());
        tmdbMovies.forEach(obj -> {
            assertTrue(obj.getRuntime() >= 120);
            assertTrue(obj.getVoteAverage().compareTo(new BigDecimal(6)) <= 0);
        });
    }

    @Test
    void listDistinctByField() {
        List<String> distinctByField = tmdbMoviesService.listDistinctByField("original_language");
        assertEquals(37, distinctByField.size());
    }

    @Test
    void listTextMatchAndOrderYearAsc() {
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.listTextMatchAndOrderYearAsc("黄金");
        assertEquals(1, tmdbMovies.size());
        List<TMDBMovies> cnMovies = tmdbMoviesService.listTextMatchAndOrderYearAsc("cn");
        assertEquals(12, cnMovies.size());
        List<TMDBMovies> zhMovies = tmdbMoviesService.listTextMatchAndOrderYearAsc("zh");
        assertEquals(27, zhMovies.size());
    }

    @Test
    void dropCollection() {
        assertTrue(tmdbMoviesService.dropCollection(TMDBMovies.class));
    }

    @Test
    void createTextIndex() {
        tmdbMoviesService.createTextIndex("original_language");
    }

    @Test
    void dropTextIndex() {
        assertTrue(tmdbMoviesService.dropTextIndex("title","keywords.name","original_language"));
    }

    @Test
    void dropAllIndex() {
        tmdbMoviesService.dropAllIndex();
        List<IndexInfo> indexInfos = mongoTemplate.indexOps(TMDBMovies.class).getIndexInfo();
        // 默认有一个 _id_ 索引
        indexInfos.removeIf(obj -> obj.getName().equals("_id_"));
        assertEquals(0, indexInfos.size());
    }
}