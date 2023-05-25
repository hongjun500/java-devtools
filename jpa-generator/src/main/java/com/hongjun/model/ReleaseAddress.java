package com.hongjun.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 首映地区
 */

@Data
@Entity
@Table(name = "release_address")
public class ReleaseAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
}
