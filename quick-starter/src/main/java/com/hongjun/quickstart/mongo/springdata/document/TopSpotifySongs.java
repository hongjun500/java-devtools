package com.hongjun.quickstart.mongo.springdata.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author hongjun500
 * @date 2023/11/3 16:26
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
@Document(value = "top_spotify_songs")
public class TopSpotifySongs {

    @Field(value = "spotify_id")
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String spotifyId;


    @Field(value = "name")
    private String name;

    @Field(value = "artists")
    private String artists;

    @Field(value = "daily_rank")
    private Integer dailyRank;

    @Field(value = "daily_movement")
    private Integer dailyMovement;

    @Field(value = "weekly_movement")
    private Integer weeklyMovement;

    @Field(value = "country")
    private String country;

    @Field(value = "snapshot_date")
    private String snapshotDate;

    @Field(value = "popularity")
    private Integer popularity;

    @Field(value = "is_explicit")
    private Boolean isExplicit;

    @Field(value = "duration_ms")
    private Integer durationMs;

    @Field(value = "album_name")
    private String albumName;

    @Field(value = "album_release_date")
    private String albumReleaseDate;

    @Field(value = "danceability")
    private Double danceability;

    @Field(value = "energy")
    private Double energy;

    @Field(value = "key")
    private Integer key;

    @Field(value = "loudness")
    private Double loudness;

    @Field(value = "mode")
    private Integer mode;

    @Field(value = "speechiness")
    private Double speechiness;

    @Field(value = "acousticness")
    private Double acousticness;

    @Field(value = "instrumentalness")
    private String instrumentalness;

    @Field(value = "liveness")
    private Double liveness;

    @Field(value = "valence")
    private Double valence;

    @Field(value = "tempo")
    private Double tempo;

    @Field(value = "time_signature")
    private Integer timeSignature;


}
