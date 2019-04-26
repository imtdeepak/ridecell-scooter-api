package com.ridecell.scooter.repositories;

import com.ridecell.scooter.entities.Scooter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by deepakkumar on 4/24/19.
 */
public interface ScooterRepository extends CrudRepository<Scooter, Long> {

    public List<Scooter> findAllScooterByReserved(Boolean reserved);
}
