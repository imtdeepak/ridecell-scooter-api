package com.ridecell.scooter.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by deepakkumar on 4/24/19.
 */

@Data
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName, lastName;

    @Column(unique=true)
    private String email;

}
