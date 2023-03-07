package com.hongjun.index.service;

import com.hongjun.AppMain;
import com.hongjun.index.Book;
import com.hongjun.index.base.BaseIndexService;
import com.hongjun.index.document.Movie;
import com.hongjun.service.MovieIndexService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Log4j2
@SpringBootTest(classes = AppMain.class)
class BaseIndexServiceTest {

    @Autowired
    BaseIndexService baseIndexService;
    @Autowired
    MovieIndexService movieIndexService;

    @Test
    void initIndexAndMapping() {
        boolean indexAndMapping = baseIndexService.initIndexAndMapping(Movie.class);
        // boolean indexAndMapping = baseIndexService.initIndexAndMapping(Book.class);
        assert indexAndMapping;
    }

    @Test
    void refreshDataToEs() throws IOException {
        List<Movie> movieList = movieIndexService.convertCSVtoList("tmdb_5000_movies.csv");
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            log.info(movie.toString());
        }
        baseIndexService.refreshDataToEs(movieList, Movie.class);

    }

    @Test
    void delIndex() {
        // boolean delIndex = baseIndexService.delIndex(Movie.class);
        boolean delIndex = baseIndexService.delIndex(Book.class);
        assert delIndex;
    }
}