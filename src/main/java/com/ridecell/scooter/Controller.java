package com.ridecell.scooter;


import com.ridecell.scooter.entities.Customer;
import com.ridecell.scooter.entities.Scooter;
import com.ridecell.scooter.services.CustomerService;
import com.ridecell.scooter.services.ScooterService;
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
@RequestMapping(value = "/api/v1")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScooterService scooterService;

    //GET  /api/v1/scooters/available?lat=37.788989&lng=-122.404810&radius=20.0

    @RequestMapping(value = {"/scooters/available"}, params = {"lat", "lng", "radius"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllScooters(@RequestParam("lat") double lat, @RequestParam("lng") double lng, @RequestParam("radius") double radius) {
        List<Scooter> scooters = scooterService.getAllScooters(lat, lng, radius);
        return new ResponseEntity<Object>(scooters, HttpStatus.OK);
    }

    //POST /api/v1/scooters/reserve?id=10

    @RequestMapping(value = {"/scooters/reserve/{id}"})
    public ResponseEntity<?> reserve(@PathVariable Long id) {
        String reserveStatus = scooterService.reserve(id);
        return new ResponseEntity<Object>(reserveStatus, HttpStatus.OK);
    }

    @RequestMapping(value = {"/scooters/return/{id}"})
    public ResponseEntity<?> returnScooter(@PathVariable Long id) {
        String returnStatus = scooterService.returnScooter(id);
        return new ResponseEntity<Object>(returnStatus, HttpStatus.OK);
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

