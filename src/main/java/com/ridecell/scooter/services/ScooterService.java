package com.ridecell.scooter.services;

import com.ridecell.scooter.entities.Scooter;
import com.ridecell.scooter.repositories.ScooterRepository;
import com.ridecell.scooter.utils.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


    public Scooter saveScooter(Scooter scooter) {
        scooter = scooterRepository.save(scooter);
        return scooter;
    }

    public List<Scooter> getAllScooters(double lat, double lng, double radius) {
        List<Scooter> scooters = scooterRepository.findAllScooterByReserved(Boolean.FALSE);
        List<Scooter> availableScooters = new ArrayList<>();
        for (Scooter scooter : scooters) {
            if (scooterInRadius(scooter, lat, lng, radius)) {
                availableScooters.add(scooter);
            }
        }
        return availableScooters;
    }

    private boolean scooterInRadius(Scooter scooter, double lat, double lng, double radius) {
        if (DistanceUtil.distanceBetweenPoints(scooter.getLastLatitude(), scooter.getLastLongitude(), lat, lng, 'N') < radius) {
            return true;
        } else
            return false;
    }

    public String reserve(Long id) {
        Scooter scooter = getScooter(id);
        if (scooter == null) {
            return "Incorrect Scooter ID.";
        } else if (scooter.getReserved()) {
            return "Already reserved Use other scooter.";
        } else {
            scooter.setReserved(Boolean.TRUE);
            scooter = saveScooter(scooter);
            return scooter.getId() + " Reserved Successfully.";

        }

    }

    public String returnScooter(Long id) {
        Scooter scooter = getScooter(id);
        if (scooter == null) {
            return "Incorrect Scooter ID";
        } else if (scooter.getReserved()) {
            scooter.setReserved(Boolean.FALSE);
            scooter = saveScooter(scooter);
            return scooter.getId() + " Returned Successfully.";
        } else {
            return "Already returned.";
        }

    }
}
