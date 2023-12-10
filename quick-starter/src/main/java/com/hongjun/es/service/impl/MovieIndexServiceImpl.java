package com.hongjun.es.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.google.common.collect.Lists;
import com.hongjun.es.document.*;
import com.hongjun.es.service.MovieIndexService;
import com.hongjun.index.base.BaseIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MovieIndexServiceImpl implements MovieIndexService {

    @Autowired
    private BaseIndexService baseIndexService;
    @Override
    public List<Movie> convertCSVtoList(String csvPath) throws IOException {
        StopWatch stopWatch = new StopWatch("convertCSVtoList");
        if (StrUtil.isBlank(csvPath)) {
            return Lists.newArrayList();
        }
        File file = new ClassPathResource(csvPath).getFile();
        Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
        List<Map<String, String>> maps = CsvUtil.getReader().readMapList(reader);
        List<Movie> list = Lists.newArrayListWithExpectedSize(maps.size());
        stopWatch.start();
        for (Map<String, String> map : maps) {
            Movie movie = new Movie();
            setMovieProperties(movie, map);
            list.add(movie);
        }
        /*list.addAll(maps.stream().map(map ->{
            Movie movie = new Movie();
            setMovieProperties(movie, map);
            return movie;
        }).toList());*/

        stopWatch.stop();
        log.info("convertCSVtoList方法耗时{}", stopWatch.getLastTaskTimeMillis() + "毫秒");
        return list;
    }

    private int parseInt(Map<String, String> map, String key) {
        String value = map.get(key);
        return StrUtil.isNotBlank(value) ? Integer.parseInt(value) : 0;
    }

    private double parseDouble(Map<String, String> map, String key) {
        String value = map.get(key);
        return StrUtil.isNotBlank(value) ? Double.parseDouble(value) : 0;
    }

    private LocalDate parseLocalDate(Map<String, String> map, String key) {
        String value = map.get(key);
        return StrUtil.isBlank(value) ? LocalDate.now() : LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
    }

    private <T> List<T> parseList(Map<String, String> map, String key, Class<T> clazz) {
        String value = map.get(key);
        return JSONArray.parseArray(value).toList(clazz);
    }

    // 将读取的变量和Movie对象的设置封装到一个方法中
    private void setMovieProperties(Movie movie, Map<String, String> map) {
        movie.setId(map.get("id"));
        movie.setTitle(map.get("title"));
        movie.setBudget(map.get("budget"));
        movie.setOverview(map.get("overview"));
        movie.setPopularity(parseDouble(map, "popularity"));
        movie.setRevenue(parseDouble(map, "revenue"));
        movie.setRuntime(map.get("runtime"));
        movie.setStatus(map.get("status"));
        movie.setOriginalLanguage(map.get("original_language"));
        movie.setOriginalTitle(map.get("original_title"));
        movie.setTagline(map.get("tagline"));
        movie.setVoteAverage(parseDouble(map, "vote_average"));
        movie.setVoteCount(parseInt(map, "vote_count"));
        movie.setReleaseDate(parseLocalDate(map, "release_date"));
        movie.setGenres(parseList(map, "genres", Genres.class));
        movie.setKeywords(parseList(map, "keywords", Keyword.class));
        movie.setProductionCompanies(parseList(map, "production_companies", ProductionCompany.class));
        movie.setSpokenLanguages(parseList(map, "spoken_languages", SpokenLanguage.class));
    }

    @Override
    public <T> void setListToEs(List<T> list) {
        // 初始化索引和mapping
        boolean indexAndMapping = baseIndexService.initIndexAndMapping(list.getClass());
        if (indexAndMapping) {

        }
    }
}
