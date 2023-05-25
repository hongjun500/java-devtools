package com.hongjun.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 关键词
 */

@Data
@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关键词名称
     */
    private String name;
}
