package com.ridecell.scooter.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by deepakkumar on 4/24/19.
 */
@Data
@Entity
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String license;

    private String model;

    private Integer year;

    private Double lastLatitude, lastLongitude;

    private Boolean reserved;

}
