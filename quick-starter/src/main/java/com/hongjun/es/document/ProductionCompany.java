package com.hongjun.es.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 制片方
 */

@Data
public class ProductionCompany {
    @Field(type = FieldType.Integer)
    private Integer id;

    /**
     * 名称
     */
    @Field(type = FieldType.Keyword)
    private String name;
}
