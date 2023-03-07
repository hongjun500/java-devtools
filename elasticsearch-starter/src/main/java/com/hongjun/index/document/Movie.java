package com.hongjun.index.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

/**
 * 电影-主索引
 */

@Data
@Document(indexName = "movie")
public class Movie {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    /**
     * 预算
     */
    @Field(type = FieldType.Long)
    private String budget;

    /**
     * 原语言
     */
    @Field(type = FieldType.Keyword)
    private String originalLanguage;

    /**
     * 原标题
     */
    @Field(type = FieldType.Keyword)
    private String originalTitle;

    /**
     * 概述
     */
    @Field(type = FieldType.Text)
    private String overview;

    /**
     * 流行度
     */
    @Field(type = FieldType.Double)
    private Double popularity;

    @Field(type = FieldType.Date, pattern = "yyyy/M/d")
    private LocalDate releaseDate;

    /**
     * 收入
     */
    @Field(type = FieldType.Double)
    private Double revenue;

    /**
     * 播放时常，分钟
     */
    @Field(type = FieldType.Keyword)
    private String runtime;

    /**
     * 状态
     */
    @Field(type = FieldType.Keyword)
    private String status;

    /**
     * 标题语
     */
    @Field(type = FieldType.Keyword)
    private String tagline;

    /**
     * 标题
     */
    @Field(type = FieldType.Keyword)
    private String title;

    /**
     * 投票平均分
     */
    @Field(type = FieldType.Double)
    private Double voteAverage;

    /**
     * 投票计数
     */
    @Field(type = FieldType.Integer)
    private Integer voteCount;

    /**
     * 首映地区
     */
    @Field(type = FieldType.Object)
    private ReleaseAddress releaseAddress;

    /**
     * 包含种类
     */
    @Field(type = FieldType.Nested)
    private List<Genres> genres;

    /**
     * 关键词
     */
    @Field(type = FieldType.Nested)
    private List<Keyword> keywords;

    /**
     * 制片方
     */
    @Field(type = FieldType.Nested)
    private List<ProductionCompany> productionCompanies;

    /**
     * 口语
     */
    @Field(type = FieldType.Nested)
    private List<SpokenLanguage> spokenLanguages;
}
