package com.hongjun.mongodb.springdata.document;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hongjun500
 * @date 2024-01-30 21:56
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: Properties
 */

@lombok.Data
@Document(collection = "properties")
public class Properties {
    private String id;
    private String name;
    private String value;
}
