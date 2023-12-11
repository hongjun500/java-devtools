package com.hongjun.quickstart.es.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 类型
 */

@Data
public class Genres {

    @Field(type = FieldType.Integer)
    private Integer id;

    /**
     * 类型名称
     */
    @Field(type = FieldType.Keyword)
    private String name;
}
