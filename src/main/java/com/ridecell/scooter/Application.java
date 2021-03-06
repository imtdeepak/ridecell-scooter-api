package com.ridecell.scooter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Created by deepakkumar on 4/24/19.
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.ridecell.scooter.entities"})
@EnableJpaRepositories(basePackages = {"com.ridecell.scooter.repositories"})

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
