package com.hongjun.common;

import com.hongjun.QuickStarterApplication;
import com.hongjun.common.es.index.Book;
import com.hongjun.quickstart.es.document.Movie;
import com.hongjun.quickstart.es.service.MovieIndexService;
import com.hongjun.index.base.BaseIndexService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CommonEsTest {

    @Autowired
    private BaseIndexService baseIndexService;

    @Autowired
    private MovieIndexService movieIndexService;

    @Test
    void testIndex() {

        boolean indexAndMapping = baseIndexService.initIndexAndMapping(Book.class);
        assert indexAndMapping;
    }

    @Test
    void testPutData() throws IOException {
        List<Movie> movieList = movieIndexService.convertCSVtoList("tmdb_5000_movies.csv");
        baseIndexService.refreshDataToEs(movieList, Movie.class);

    }

    @Test
    void delIndex() {
        boolean indexAndMapping = baseIndexService.delIndex(Book.class);
        assert indexAndMapping;
    }
}
