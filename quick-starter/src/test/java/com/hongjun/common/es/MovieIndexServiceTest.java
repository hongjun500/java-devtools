package com.hongjun.common.es;

import com.hongjun.EsAppMain;
import com.hongjun.index.document.Movie;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Log4j2
@SpringBootTest(classes = EsAppMain.class)
class MovieIndexServiceTest {

    final String csvPath = "tmdb_5000_movies.csv";

    @Autowired
    private MovieIndexService movieIndexService;


    @Test
    void testInit() throws IOException {
        log.info("你好");
    }

    @Test
    void testImportIndexDocForCSV() throws IOException {
        List<Movie> movieList = movieIndexService.convertCSVtoList(csvPath);
        log.info("movieList.getClass={}",movieList.getClass());
    }
}