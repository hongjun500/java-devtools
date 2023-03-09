package com.hongjun.es.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 关键词
 */

@Data
public class Keyword {

    @Field(type = FieldType.Integer)
    private Integer id;

    /**
     * 关键词名称
     */
    @Field(type = FieldType.Keyword)
    private String name;
}
