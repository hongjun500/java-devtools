package com.hongjun.es.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 口语
 */
@Data
public class SpokenLanguage {
    @Field(type = FieldType.Keyword)
    private String iso_639_1;

    /**
     * 名称
     */
    @Field(type = FieldType.Keyword)
    private String name;
}
