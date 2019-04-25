package com.ridecell.scooter.services;

import com.ridecell.scooter.entities.Customer;
import com.ridecell.scooter.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by deepakkumar on 4/24/19.
 */
@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(Long id) {
        Customer customer = customerRepository.findOne(id);
        return customer;

    }

    public Iterable<Customer> getAllCustomers() {

        Iterable<Customer> customers = customerRepository.findAll();
        return customers;

    }

    public Customer saveCustomer(Customer customer) {
        customer = customerRepository.save(customer);
        return customer;
    }
}
