package com.ridecell.scooter.repositories;

import com.ridecell.scooter.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
