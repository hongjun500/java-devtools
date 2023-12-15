package com.hongjun.mongodb.springdata.document;

import com.hongjun.util.convert.json.CommonFastJsonUtil;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author hongjun500
 * @date 2023/12/12 16:29
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
@Document(collection = "tmdb_movies")
public class TMDBMovies {

    @MongoId(value = FieldType.STRING)
    private String id;

    private Long budget;

    private List<Genre> genres;

    private String homepage;

    private List<Keyword> keywords;

    @Field(value = "original_language")
    private String originalLanguage;

    @Field(value = "original_title")
    private String originalTitle;

    private String overview;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal popularity;

    @Field(value = "production_companies")
    private List<ProductionCompany> productionCompanies;

    @Field(value = "production_countries")
    private List<ProductionCountry> productionCountries;

    @Field(value = "release_date")
    private LocalDate releaseDate;

    private Long revenue;

    private Integer runtime;

    @Field(value = "spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    private String status;

    private String tagline;

    private String title;

    // private String video;

    @Field(value = "vote_average", targetType = FieldType.DECIMAL128)
    private BigDecimal voteAverage;

    @Field(value = "vote_count")
    private Integer voteCount;


    @Data
    public static class Genre {
        private String id;
        private String name;
    }

    @Data
    public static class Keyword {
        private String id;
        private String name;
    }

    @Data
    public static class ProductionCompany {
        private String id;
        private String name;
    }

    @Data
    public static class ProductionCountry {
        private String iso_3166_1;
        private String name;
    }

    @Data
    public static class SpokenLanguage {
        private String iso_639_1;
        private String name;
    }

    public List<Keyword> convertKeywords(String keywords) {
        // todo Java8
        return List.of(CommonFastJsonUtil.fromJsonArray(keywords, Keyword[].class));
    }

    public List<Genre> convertGenres(String genres) {
        // todo Java8
        return List.of(CommonFastJsonUtil.fromJsonArray(genres, Genre[].class));
    }

    public static <T> List<T> convertToList(String json, Class<T[]> targetType) {
        return List.of(CommonFastJsonUtil.fromJsonArray(json, targetType));
    }
}
