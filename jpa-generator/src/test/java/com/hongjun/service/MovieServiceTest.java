package com.hongjun.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.google.common.collect.Lists;
import com.hongjun.JpaGeneratorApp;
import com.hongjun.model.*;
import com.hongjun.util.convert.json.CommonFastJsonUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest(classes = JpaGeneratorApp.class)
class MovieServiceTest {

	public static final String CSV_PATH = "tmdb_5000_movies.csv";

	@Autowired
	private MovieService movieService;

	@Test
	void saveMovies() throws IOException {
		StopWatch stopWatch = new StopWatch("convertCSVtoList");

		if (StrUtil.isBlank(CSV_PATH)) {
			return;
		}
		File file = new ClassPathResource(CSV_PATH).getFile();
		Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
		List<Map<String, String>> maps = CsvUtil.getReader().readMapList(reader);
		List<Movie> list = Lists.newArrayListWithExpectedSize(maps.size());
		stopWatch.start();
		for (Map<String, String> map : maps) {
			Movie movie = new Movie();
			setMovieProperties(movie, map);
			list.add(movie);
		}
		stopWatch.stop();
		log.info("convertCSVtoList方法耗时{}", stopWatch.getLastTaskTimeMillis() + "毫秒");
		movieService.saveMovies(list);
	}

	private void setMovieProperties(Movie movie, Map<String, String> map) {
		movie.setId(MapUtil.getInt(map, "id"));
		movie.setTitle(map.get("title"));
		movie.setBudget(map.get("budget"));
		movie.setOverview(map.get("overview"));
		movie.setPopularity(MapUtil.getDouble(map, "popularity"));
		movie.setRevenue(MapUtil.getDouble(map, "revenue"));
		movie.setRuntime(map.get("runtime"));
		movie.setStatus(map.get("status"));
		movie.setOriginalLanguage(map.get("original_language"));
		movie.setOriginalTitle(map.get("original_title"));
		movie.setTagline(map.get("tagline"));
		movie.setVoteAverage(MapUtil.getDouble(map, "vote_average"));
		movie.setVoteCount(MapUtil.getInt(map, "vote_count"));
		movie.setReleaseDate(parseLocalDate(map, "release_date"));
		movie.setReleaseAddress(new ArrayList<>());
		movie.setGenres(parseList(map, "genres", Genres.class));
		movie.setKeywords(parseList(map, "keywords", Keyword.class));
		movie.setProductionCompanies(parseList(map, "production_companies", ProductionCompany.class));
		movie.setSpokenLanguages(parseList(map, "spoken_languages", SpokenLanguage.class));
	}

	private LocalDate parseLocalDate(Map<String, String> map, String key) {
		String value = map.get(key);
		return StrUtil.isBlank(value) ? LocalDate.now() : LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
	}
	private <T> List<T> parseList(Map<String, String> map, String key, Class<T> clazz) {
		String value = map.get(key);
		return CommonFastJsonUtil.toJsonArray(value).toList(clazz);
	}
}