package com.hongjun.index.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 首映地区
 */

@Data
public class ReleaseAddress {

    @Field(type = FieldType.Keyword)
    private String address;
}
