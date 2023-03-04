package com.hongjun.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.hongjun.index.document.*;
import com.hongjun.service.MovieIndexService;
import com.hongjun.util.ObjectPoolUtil;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
@Service
public class MovieIndexServiceImpl implements MovieIndexService {
    @Override
    public List<Movie> convertCSVtoList(String csvPath) throws IOException {
        StopWatch stopWatch = new StopWatch("convertCSVtoList");
        if (StrUtil.isBlank(csvPath)) {
            return Lists.newArrayList();
        }
        File file = new ClassPathResource(csvPath).getFile();
        Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
        List<Map<String, String>> maps = CsvUtil.getReader(reader).readMapList(reader);
        stopWatch.start();
        List<Movie> list = Lists.newArrayListWithExpectedSize(maps.size());
        for (Map<String, String> map : maps) {
            Movie movie = new Movie();
            movie.setId(Integer.parseInt(map.get("id")));
            movie.setTitle(map.get("title"));
            movie.setBudget(map.get("budget"));
            movie.setOverview(map.get("overview"));
            movie.setPopularity(Double.parseDouble(map.get("popularity")));
            movie.setRevenue(Double.parseDouble(map.get("revenue")));
            movie.setRuntime(map.get("runtime"));
            movie.setStatus(map.get("status"));
            movie.setOriginalLanguage(map.get("original_language"));
            movie.setOriginalTitle(map.get("original_title"));
            movie.setReleaseAddress(null);
            movie.setTagline(map.get("tagline"));
            movie.setVoteAverage(Double.parseDouble(map.get("vote_average")));
            movie.setVoteCount(Integer.parseInt(map.get("vote_count")));
            String releaseDate = map.get("release_date");
            if (StrUtil.isBlank(releaseDate)) {
                releaseDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            }
            movie.setReleaseDate(LocalDate.parse(releaseDate));

            // 聚合的数据
            List<Genres> genresList = JSONUtil.toList(map.get("genres"), Genres.class);
            movie.setGenres(genresList);

            List<Keyword> keywordList = JSONUtil.toList(map.get("keywords"), Keyword.class);
            movie.setKeywords(keywordList);

            List<ProductionCompany> productionCompanyList = JSONUtil.toList(map.get("production_companies"), ProductionCompany.class);
            movie.setProductionCompanies(productionCompanyList);

            List<SpokenLanguage> spokenLanguageList = JSONUtil.toList(map.get("spoken_languages"), SpokenLanguage.class);
            movie.setSpokenLanguages(spokenLanguageList);

            list.add(movie);
        }
        for (Movie movie : list) {
            log.info("movie-ID = 【{}】,【{}】=",movie.getId(), JSONUtil.toJsonStr(movie));
        }
        stopWatch.stop();
        log.info("convertCSVtoList方法耗时{}", stopWatch.getTotalTimeMillis() + "毫秒");
        return list;
    }

    @Override
    public List<Movie> convertCSVtoList2(String csvPath) throws IOException {
        StopWatch stopWatch = new StopWatch("convertCSVtoList2");
        if (StrUtil.isBlank(csvPath)) {
            return Lists.newArrayList();
        }
        File file = new ClassPathResource(csvPath).getFile();
        Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
        List<Map<String, String>> maps = CsvUtil.getReader(reader).readMapList(reader);
        stopWatch.start();
        ObjectPoolUtil<Movie> objectPoolUtil = new ObjectPoolUtil<>(maps.size(), Movie::new, (args) -> new Movie());

        List<Movie> list = Lists.newArrayListWithExpectedSize(maps.size());
        for (int i = 0; i < maps.size(); i++) {
            Map<String, String> map = maps.get(i);
            Movie movie = objectPoolUtil.borrowObject();
            movie.setId(Integer.parseInt(map.get("id")));
            movie.setTitle(map.get("title"));
            movie.setBudget(map.get("budget"));
            movie.setOverview(map.get("overview"));
            movie.setPopularity(Double.parseDouble(map.get("popularity")));
            movie.setRevenue(Double.parseDouble(map.get("revenue")));
            movie.setRuntime(map.get("runtime"));
            movie.setStatus(map.get("status"));
            movie.setOriginalLanguage(map.get("original_language"));
            movie.setOriginalTitle(map.get("original_title"));
            movie.setReleaseAddress(null);
            movie.setTagline(map.get("tagline"));
            movie.setVoteAverage(Double.parseDouble(map.get("vote_average")));
            movie.setVoteCount(Integer.parseInt(map.get("vote_count")));
            String releaseDate = map.get("release_date");
            if (StrUtil.isBlank(releaseDate)) {
                releaseDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            }
            movie.setReleaseDate(LocalDate.parse(releaseDate));

            // 聚合的数据
            List<Genres> genresList = JSONUtil.toList(map.get("genres"), Genres.class);
            movie.setGenres(genresList);

            List<Keyword> keywordList = JSONUtil.toList(map.get("keywords"), Keyword.class);
            movie.setKeywords(keywordList);

            List<ProductionCompany> productionCompanyList = JSONUtil.toList(map.get("production_companies"), ProductionCompany.class);
            movie.setProductionCompanies(productionCompanyList);

            List<SpokenLanguage> spokenLanguageList = JSONUtil.toList(map.get("spoken_languages"), SpokenLanguage.class);
            movie.setSpokenLanguages(spokenLanguageList);

            list.add(movie);
            objectPoolUtil.returnObject(movie);
        }
         for (Movie movie : list) {
            log.info("movie-ID = 【{}】,【{}】=",movie.getId(), JSONUtil.toJsonStr(movie));
        }
        stopWatch.stop();
        log.info("convertCSVtoList2方法耗时{}", stopWatch.getTotalTimeMillis() + "毫秒");
        return list;
    }
}
