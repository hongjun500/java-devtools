package com.hongjun.mongodb.springdata.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author hongjun500
 * @date 2024-01-30 21:25
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: User
 */
@lombok.Data
@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String name;

    /**
     * 这里的 DBRef 代表对另一个文档的引用
     * 在通过引用的属性查询当前文档数据时需要通过 [当前示例：role.$id] 来查询
     * 并且传值时需要传入一个 ObjectId 对象 (前提是被引用的文档的主键是 ObjectId 类型)
     */
    @DBRef(lazy = true)
    private Role role;

    /**
     * 同上
     */
    @DBRef(lazy = true)
    private List<Properties> properties;
}
