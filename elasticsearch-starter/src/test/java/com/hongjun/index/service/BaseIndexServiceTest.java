package com.hongjun.index.service;

import com.hongjun.AppMain;
import com.hongjun.index.base.BaseIndexService;
import com.hongjun.index.document.Movie;
import com.hongjun.service.MovieIndexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = AppMain.class)
class BaseIndexServiceTest {

    @Autowired
    BaseIndexService baseIndexService;
    @Autowired
    MovieIndexService movieIndexService;

    @Test
    void initIndexAndMapping() {
        boolean indexAndMapping = baseIndexService.initIndexAndMapping(Movie.class);
        assert indexAndMapping;
    }

    @Test
    void refreshDataToEs() throws IOException {
        List<Movie> movieList = movieIndexService.convertCSVtoList("tmdb_5000_movies.csv");
        baseIndexService.refreshDataToEs(movieList, Movie.class);

    }
}