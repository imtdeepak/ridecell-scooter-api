package com.ridecell.scooter.services;

import com.ridecell.scooter.entities.Scooter;
import com.ridecell.scooter.repositories.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by deepakkumar on 4/24/19.
 */
@Component
public class ScooterService {

    @Autowired
    private ScooterRepository scooterRepository;

    public Scooter getScooter(Long id) {
        Scooter scooter = scooterRepository.findOne(id);
        return scooter;

    }

    public Iterable<Scooter> getAllCustomers() {

        Iterable<Scooter> scooters = scooterRepository.findAll();
        return scooters;

    }

    public Scooter saveCustomer(Scooter scooter) {
        scooter = scooterRepository.save(scooter);
        return scooter;
    }

    public List<Scooter> getAllScooters(double lat, double lng, double radius) {
        List<Scooter> scooters = scooterRepository.findAllScooterByReserved(Boolean.FALSE);

        return scooters;
    }

    public String reserve(Long id) {
        Scooter scooter = scooterRepository.findOne(id);
        if (scooter == null) {
            return "Incorrect Scooter ID";
        } else if (scooter.getReserved()) {
            return "Already reserved Use other scooter";
        } else {
            scooter.setReserved(Boolean.TRUE);
            scooter = scooterRepository.save(scooter);
            return "Reserved Successfully";

        }

    }

    public String returnScooter(Long id) {
        Scooter scooter = scooterRepository.findOne(id);
        if (scooter == null) {
            return "Incorrect Scooter ID";
        } else if (scooter.getReserved()) {
            scooter.setReserved(Boolean.FALSE);
            scooter = scooterRepository.save(scooter);
            return "Returned Successfully.";
        } else {
            return "Already returned.";
        }

    }
}
