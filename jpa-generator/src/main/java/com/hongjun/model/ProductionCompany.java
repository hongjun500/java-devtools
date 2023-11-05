package com.hongjun.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 制片方
 */

@Data
@Entity
@Table(name = "production_company")
public class ProductionCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;
}
