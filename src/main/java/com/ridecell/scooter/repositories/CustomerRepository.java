package com.ridecell.scooter.repositories;

import com.ridecell.scooter.entities.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by deepakkumar on 4/24/19.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
