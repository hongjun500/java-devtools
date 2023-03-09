package com.hongjun.common.es;

import com.hongjun.QuickStarterApplication;
import com.hongjun.es.MovieRepository;
import com.hongjun.es.document.Movie;
import com.hongjun.es.service.MovieIndexService;
import com.hongjun.index.base.BaseIndexService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class)
class MovieIndexServiceTest {

    final String csvPath = "tmdb_5000_movies.csv";

    @Autowired
    private MovieIndexService movieIndexService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BaseIndexService baseIndexService;


    @Test
    void testInit() throws IOException {
        log.info("你好");
    }

    @Test
    void testImportIndexDocForCSV() throws IOException {
        List<Movie> movieList = movieIndexService.convertCSVtoList(csvPath);
        log.info("movieList.getClass={}", movieList.getClass());
        baseIndexService.refreshDataToEs(movieList, Movie.class);
    }



    @Test
    void del() {
        movieRepository.deleteAll();
    }

    @Test
    void listAll() {
        List<Movie> movieList = movieRepository.findAll();
        long count = movieRepository.count();
    }
}