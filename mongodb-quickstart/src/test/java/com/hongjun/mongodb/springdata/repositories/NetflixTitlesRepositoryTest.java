package com.hongjun.mongodb.springdata.repositories;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hongjun.mongodb.common.conf.MongoConfig;
import com.hongjun.mongodb.springdata.document.NetflixTitles;
import com.hongjun.mongodb.util.FileResourcesUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataMongoTest
@ImportAutoConfiguration(MongoConfig.class)
// @ContextConfiguration(classes = MongoDBConfiguration.class)
class NetflixTitlesRepositoryTest {

    private static NetflixTitles tempNetflixTitles;

    private static final String SHOW_ID_S1 = "s1";
    private static final String EDITED_SHOW_ID_S1 = "s1+";

    @Autowired
    private NetflixTitlesRepository netflixTitlesRepository;


    @BeforeEach
    void setUp() {
        Assertions.assertAll(() -> {
            assertNotNull(netflixTitlesRepository);
        });
        if (tempNetflixTitles == null) {
            List<NetflixTitles> s1 = netflixTitlesRepository.findAllByShowId(SHOW_ID_S1);
            if (s1.isEmpty()) {
                NetflixTitles netflixTitles = new NetflixTitles();
                netflixTitles.setId(SHOW_ID_S1);
                netflixTitles.setShowId(SHOW_ID_S1);
                netflixTitles.setType("Movie");
                netflixTitles.setTitle("Dick Johnson Is Dead");
                netflixTitles.setReleaseYear("2019");
                netflixTitles.setDuration("90 min");
                netflixTitles.setDirector("Kirsten Johnson");
                netflixTitles.setCast("");
                netflixTitles.setCountry("United States");
                netflixTitles.setDateAdded("September 25, 2021");
                netflixTitles.setRating("PG-13");
                netflixTitles.setListedIn("Documentaries");
                netflixTitles.setDescription("As her father nears the end of his life, filmmaker Kirsten Johnson stages his death in inventive and comical ways to help them both face the inevitable.");
                tempNetflixTitles = netflixTitles;
            } else {
                tempNetflixTitles = s1.get(0);
            }
        }
    }

    @Test
    void testImportAllCsv() throws IOException {
        List<Map<String, String>> maps = FileResourcesUtil.readCSV("mongo/csv/netflix_titles.csv");
        List<NetflixTitles> netflixTitles = netflixTitlesRepository.saveAll(maps.stream().map(map -> {
            NetflixTitles titles = new NetflixTitles();
            String showId = map.get("show_id");
            titles.setId(showId);
            titles.setShowId(showId);
            titles.setType(map.get("type"));
            titles.setTitle(map.get("title"));
            titles.setDirector(map.get("director"));
            titles.setCast(map.get("cast"));
            titles.setCountry(map.get("country"));
            titles.setDateAdded(map.get("date_added"));
            titles.setRating(map.get("rating"));
            titles.setListedIn(map.get("listed_in"));
            titles.setDescription(map.get("description"));
            titles.setReleaseYear(map.get("release_year"));
            titles.setDuration(map.get("duration"));
            return titles;
        }).toList());
        assertEquals(8807, netflixTitles.size());
    }

    @Test
    void testFindAll() {
        List<NetflixTitles> all = netflixTitlesRepository.findAll();
        assertNotNull(all);
        assertEquals(8807, all.size());
    }

    @Test
    void testPagination() {
        Sort releaseYear = Sort.by("releaseYear").ascending();
        PageRequest pageRequest = PageRequest.of(0, 10, releaseYear);
        Page<NetflixTitles> page = netflixTitlesRepository.findAll(pageRequest);
        assertEquals(10, page.getSize());
        assertEquals(8807, page.getTotalElements());
        page.getContent().forEach(obj ->
                log.info("page: {}", obj)
        );
        assertEquals("1925", page.getContent().get(0).getReleaseYear());
    }

    @Test
    void testPaginationByType() {
        // Sort.by("showId").ascending().and(Sort.by("duration").descending());
        Sort sort = Sort.by(Sort.Order.asc("showId"), Sort.Order.desc("duration"));
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        Page<NetflixTitles> page = netflixTitlesRepository.findAllByTypeAndReleaseYear(pageRequest, "Movie", "2019");
        assertEquals(633, page.getTotalElements());
        page.getContent().forEach(obj -> {
            log.info("page: {}", obj);
            assertEquals("2019", obj.getReleaseYear());
            assertEquals("Movie", obj.getType());
        });
        assertEquals("s1009", page.getContent().get(0).getShowId());
    }

    @Test
    void testFindByType() {
        List<NetflixTitles> movies = netflixTitlesRepository.findByType("Movie");
        assertEquals(8807, movies.size());
        List<NetflixTitles> tvShows = netflixTitlesRepository.findByType("TV Show");
        assertEquals(8807 - 6131, tvShows.size());
    }

    @Test
    void testFindTitleLikeOrDescriptionLike() {
        List<NetflixTitles> list = netflixTitlesRepository.findAllByTitleIsLikeOrDescriptionIsLike("Gotham", "Gotham");
        assertEquals(2, list.size());
    }

    @Test
    void testFindAllByShowId() {
        List<NetflixTitles> s1 = netflixTitlesRepository.findAllByShowId(SHOW_ID_S1);
        assertEquals(1, s1.size());
        log.info("s1: {}", s1.get(0));
    }

    @Test
    void testSave() {
        NetflixTitles save = netflixTitlesRepository.save(tempNetflixTitles);
        assertNotNull(save);
        assertEquals(SHOW_ID_S1, save.getShowId());
    }

    @Test
    void testUpdate() {
        tempNetflixTitles.setShowId(EDITED_SHOW_ID_S1);
        tempNetflixTitles.setId(SHOW_ID_S1);
        NetflixTitles save = netflixTitlesRepository.save(tempNetflixTitles);
        assertEquals(EDITED_SHOW_ID_S1, save.getShowId());
        List<NetflixTitles> s1 = netflixTitlesRepository.findAllByShowId(SHOW_ID_S1);
        assertEquals(0, s1.size());
    }

    @Test
    void testDeleteByShowId() {
        Long total = netflixTitlesRepository.deleteByShowId(SHOW_ID_S1);
        assertEquals(1, total);
        List<NetflixTitles> s1 = netflixTitlesRepository.findAllByShowId(SHOW_ID_S1);
        assertEquals(0, s1.size());
    }

    @Test
    void testDeleteById(){
        netflixTitlesRepository.deleteById(SHOW_ID_S1);
        Optional<NetflixTitles> optional = netflixTitlesRepository.findById(SHOW_ID_S1);
        assertNull(optional.orElse(null));
    }


}