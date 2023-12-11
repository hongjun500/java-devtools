package com.hongjun.common.es;

import com.hongjun.QuickStarterApplication;
import com.hongjun.quickstart.es.MovieRepository;
import com.hongjun.quickstart.es.PageMovieRepository;
import com.hongjun.quickstart.es.document.Movie;
import com.hongjun.quickstart.es.service.MovieIndexService;
import com.hongjun.index.base.BaseIndexService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Slf4j
@SpringBootTest(classes = QuickStarterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MovieIndexServiceTest{

    final String csvPath = "mongodb/csv/tmdb_5000_movies.csv";

    @Autowired
    private MovieIndexService movieIndexService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BaseIndexService baseIndexService;
    @Autowired
    private PageMovieRepository pageMovieRepository;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


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
        Page<Movie> moviePage = pageMovieRepository.findAll(PageRequest.of(0, 4803));
        List<Movie> content = moviePage.getContent();
        Sort sort = Sort.sort(Movie.class).by(Movie::getId).descending();
        Iterator<Movie> iterator = pageMovieRepository.findAll(sort).iterator();
        // Predicate predicate = Movie.

    }

    @Test
    void getId() {
        Query query = baseIndexService.Query("id", "5");
        log.info("a");
    }
}