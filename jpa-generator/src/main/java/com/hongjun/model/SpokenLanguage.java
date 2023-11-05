package com.hongjun.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 口语
 */
@Data
@Entity
@Table(name = "spoken_language")
public class SpokenLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String iso_639_1;

    /**
     * 名称
     */
    private String name;
}
