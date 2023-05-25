package com.hongjun.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 电影-主索引
 */

@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 预算
     */
    private String budget;

    /**
     * 原语言
     */
    private String originalLanguage;

    /**
     * 原标题
     */
    private String originalTitle;

    /**
     * 概述
     */
    @Column(name = "overview", length = 1000)
    private String overview;

    /**
     * 流行度
     */
    private Double popularity;

    private LocalDate releaseDate;

    /**
     * 收入
     */
    private Double revenue;

    /**
     * 播放时常，分钟
     */
    private String runtime;

    /**
     * 状态
     */
    private String status;

    /**
     * 标题语
     */
    private String tagline;

    /**
     * 标题
     */
    private String title;

    /**
     * 投票平均分
     */
    private Double voteAverage;

    /**
     * 投票计数
     */
    private Integer voteCount;

    /**
     * 首映地区
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<ReleaseAddress> releaseAddress;

    /**
     * 包含种类
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Genres> genres;

    /**
     * 关键词
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Keyword> keywords;

    /**
     * 制片方
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductionCompany> productionCompanies;

    /**
     * 口语
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<SpokenLanguage> spokenLanguages;

}
