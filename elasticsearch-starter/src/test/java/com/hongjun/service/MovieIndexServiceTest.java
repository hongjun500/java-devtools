package com.hongjun.service;

import com.hongjun.AppMain;
import com.hongjun.index.document.Movie;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Log4j2
@SpringBootTest(classes = AppMain.class)
class MovieIndexServiceTest {

    final String csvPath = "tmdb_5000_movies.csv";

    @Autowired
    private MovieIndexService movieIndexService;


    @Test
    void testInit() throws IOException {
        log.info("inti");
    }

    @Test
    void testImportIndexDocForCSV() throws IOException {
        List<Movie> movieList = movieIndexService.convertCSVtoList(csvPath);

    }

    @Test
    void testImportIndexDocForCSV2() throws IOException {
        List<Movie> movieList2 = movieIndexService.convertCSVtoList2(csvPath);

    }


}