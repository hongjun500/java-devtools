package com.hongjun.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 类型
 */

@Data
@Entity
@Table(name = "genres")
public class Genres {

    @Id
    private Integer id;

    /**
     * 类型名称
     */
    private String name;
}
