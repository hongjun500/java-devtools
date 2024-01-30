package com.hongjun.mongodb.springdata.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hongjun500
 * @date 2024-01-30 21:26
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: Role
 */

@lombok.Data
@Document(collection = "role")
public class Role {
    @Id
    private String id;
    private String name;
}
