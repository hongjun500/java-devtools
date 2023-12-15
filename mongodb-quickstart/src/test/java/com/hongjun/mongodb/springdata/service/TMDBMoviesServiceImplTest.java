package com.hongjun.mongodb.springdata.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hongjun.mongodb.springdata.document.TMDBMovies;
import com.hongjun.mongodb.util.FileResourcesUtil;
import com.hongjun.response.CommonPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestExecutionListeners;

import java.io.IOException;
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

        boolean saved = tmdbMoviesService.bulkSaveAll(tmdbMovies);
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
        List<TMDBMovies> tmdbMovies = tmdbMoviesService.listByVoteAverageLte(new BigDecimal(6));
        assertEquals(2047, tmdbMovies.size());
    }

    @Test
    void delCollection() {
        assertTrue(tmdbMoviesService.delCollection());
    }
}