package com.hongjun.mongodb.springdata.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.hongjun.mongodb.springdata.document.TMDBMovies;
import com.hongjun.response.CommonPage;
import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Decimal128;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hongjun.mongodb.springdata.document.TMDBMovies.convertToList;

/**
 * @author hongjun500
 * @date 2023/12/12 16:46
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TMDBMoviesServiceImpl implements TMDBMoviesService {

    private final MongoTemplate mongoTemplate;

    @Override
    public void contextLoads() {
        log.info("this is TMDBMoviesServiceImpl");
        log.info("contextLoads");
    }

    @Override
    public List<TMDBMovies> convertToTMDBMovies(List<Map<String, String>> maps) {
        if (CollUtil.isEmpty(maps)) {
            // todo Java8
            return List.of();
        }
        List<TMDBMovies> list = Lists.newArrayListWithExpectedSize(maps.size());
        maps.forEach(objMap -> {
            TMDBMovies tmdbMovies = BeanUtil.mapToBean(objMap, TMDBMovies.class, true, CopyOptions.create().setIgnoreError(true));
            // log.debug("tmdbMovies: {}", CommonFastJsonUtil.toJson(tmdbMovies));
            tmdbMovies.setGenres(convertToList(objMap.get("genres"), TMDBMovies.Genre[].class));
            tmdbMovies.setReleaseDate(parseLocalDate(objMap, "release_date"));
            tmdbMovies.setKeywords(convertToList(objMap.get("keywords"), TMDBMovies.Keyword[].class));
            tmdbMovies.setProductionCompanies(convertToList(objMap.get("production_companies"), TMDBMovies.ProductionCompany[].class));
            tmdbMovies.setProductionCountries(convertToList(objMap.get("production_countries"), TMDBMovies.ProductionCountry[].class));
            tmdbMovies.setRevenue(Long.parseLong(objMap.get("revenue")));
            // tmdbMovies.setRuntime(BigDecimal.valueOf());
            tmdbMovies.setRuntime(Integer.parseInt("".equals(objMap.get("runtime")) ? "0" : objMap.get("runtime")));
            tmdbMovies.setSpokenLanguages(convertToList(objMap.get("spoken_languages"), TMDBMovies.SpokenLanguage[].class));
            tmdbMovies.setVoteAverage(BigDecimal.valueOf(Double.parseDouble(objMap.get("vote_average"))));
            tmdbMovies.setVoteCount(Integer.parseInt(objMap.get("vote_count")));
            list.add(tmdbMovies);
        });
        return list;
    }

    private LocalDate parseLocalDate(Map<String, String> map, String key) {
        String value = map.get(key);
        return StrUtil.isBlank(value) ? LocalDate.now() : LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public boolean bulkSaveAll(List<TMDBMovies> list) {
        BulkWriteResult executed = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, TMDBMovies.class)
                .insert(list)
                .execute();
        return executed.wasAcknowledged();
    }

    @Override
    public TMDBMovies getById(String id) {
        return mongoTemplate.findById(id, TMDBMovies.class);
    }

    @Override
    public CommonPage<TMDBMovies> listByPage(Integer pageNum, Integer pageSize) {
        Query query = new Query();
        long total = mongoTemplate.count(query, TMDBMovies.class);
        List<TMDBMovies> tmdbMovies = mongoTemplate.find(query.skip((long) pageNum * pageSize).limit(pageSize), TMDBMovies.class);
        return CommonPage.create(tmdbMovies, pageNum, pageSize, total);
    }

    @Override
    public List<TMDBMovies> listByTitleOrKeywords(String title, String keywords) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 使用正则表达式模糊查询
        if (StrUtil.isNotBlank(title)) {
            criteria.orOperator(Criteria.where("title").regex(title));
        }
        if (StrUtil.isNotBlank(keywords)) {
            criteria.orOperator(Criteria.where("keywords.name").regex(keywords));
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query, TMDBMovies.class);
    }

    @Override
    public List<TMDBMovies> listByRuntimeGte(Integer runtimeGte) {
        Query query = new Query();
        query.addCriteria(Criteria.where("runtime").gte(runtimeGte));
        return mongoTemplate.find(query, TMDBMovies.class);
    }

    @Override
    public List<TMDBMovies> listByVoteAverageLte(Object voteAverageLte) {
        Query query = new Query();
        query.addCriteria(Criteria.where("vote_average").lte(Decimal128.parse(voteAverageLte.toString())));
        return mongoTemplate.find(query, TMDBMovies.class);
    }

    @Override
    public List<TMDBMovies> listByRuntimeGteAndVoteAverageLte(Integer runtimeGte, Object voteAverageLte) {
        Query query = new Query();
        query.addCriteria(Criteria.where("runtime").gte(runtimeGte).and("vote_average").lte(Decimal128.parse(voteAverageLte.toString())));
        return mongoTemplate.find(query, TMDBMovies.class);
    }

    @Override
    public List<String> listDistinctByField(String field) {
        return mongoTemplate.query(TMDBMovies.class).distinct(field).as(String.class).all();
    }

    @Override
    public List<TMDBMovies> listTextMatchAndOrderYearAsc(String text) {
        Query query = TextQuery.queryText(new TextCriteria().matchingAny(text));
        // 排序
        query.with(Sort.by(Sort.DEFAULT_DIRECTION, "release_year"));
        return mongoTemplate.find(query, TMDBMovies.class);
    }

    @Override
    public boolean delCollection() {
        mongoTemplate.dropCollection(TMDBMovies.class);
        return true;
    }

    @Override
    public void createTextIndex(String... fields) {
        String ensureIndex = mongoTemplate.indexOps(TMDBMovies.class).ensureIndex(TextIndexDefinition.builder().onFields(fields).build());
        log.info("ensureIndex: {}", ensureIndex);
    }

    @Override
    public boolean dropTextIndex(String... fields) {
        List<IndexInfo> indexInfos = mongoTemplate.indexOps(TMDBMovies.class).getIndexInfo();
        indexInfos.forEach(obj -> {
            if (obj.isIndexForFields(List.of(fields))) {
                mongoTemplate.indexOps(TMDBMovies.class).dropIndex(obj.getName());
            }
        });
        return false;
    }

    @Override
    public void dropAllIndex() {
        mongoTemplate.indexOps(TMDBMovies.class).dropAllIndexes();
    }
}
