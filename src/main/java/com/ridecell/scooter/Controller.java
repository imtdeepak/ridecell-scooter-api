package com.ridecell.scooter;


import com.ridecell.scooter.entities.Customer;
import com.ridecell.scooter.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by deepakkumar on 4/24/19.
 */

@RestController
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/name")
    public ResponseEntity<?> getNames() {
        List<String> names = new ArrayList<String>();
        names.add("Stephen Curry");
        names.add("Kevin Durant");
        names.add("Klay Thompson");
        Payload payload = new Payload(names);
        return new ResponseEntity<Payload>(payload, HttpStatus.OK);
    }

    @RequestMapping(value = {"/configuration"}, method = RequestMethod.GET)
    public ResponseEntity<?> getConfiguration() {
        Map javaEnvironment = System.getenv();
        return new ResponseEntity<Map>(javaEnvironment, HttpStatus.OK);
    }

    @RequestMapping(value = {"/configuration/{pathVariable}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getConfigurationOf(@PathVariable String pathVariable) {
        String javaEnvironment = System.getenv(pathVariable);
        return new ResponseEntity<String>(javaEnvironment, HttpStatus.OK);
    }


    @RequestMapping(value = {"/customer/{pathVariable}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable String pathVariable) {
        Customer customer = customerService.getCustomer(Long.parseLong(pathVariable));
        return new ResponseEntity<Object>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = {"/customer"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomer() {
        Iterable<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<Object>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = {"/customer"}, method = RequestMethod.POST)
    public ResponseEntity<?> saveCustomer(@RequestBody(required = true) Customer customer) {
        customer = customerService.saveCustomer(customer);
        return new ResponseEntity<Object>(customer, HttpStatus.CREATED);
    }

}

