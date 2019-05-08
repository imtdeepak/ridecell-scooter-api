package com.ridecell.scooter.services;

import com.ridecell.scooter.entities.Customer;
import com.ridecell.scooter.entities.RidingSession;
import com.ridecell.scooter.entities.Scooter;
import com.ridecell.scooter.model.Reservation;
import com.ridecell.scooter.model.RideStatus;
import com.ridecell.scooter.repositories.CustomerRepository;
import com.ridecell.scooter.repositories.RidingSessionRepository;
import com.ridecell.scooter.repositories.ScooterRepository;
import com.ridecell.scooter.utils.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by deepakkumar on 4/24/19.
 */
@Component
public class ScooterService {

    @Autowired
    private ScooterRepository scooterRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RidingSessionRepository ridingSessionRepository;

    private Double unitPrice=0.10;

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

    public Reservation reserve(Long scooterId, Long customerId) {
        Scooter scooter = getScooter(scooterId);

        Customer customer = getCustomer(customerId);
        Reservation reservation = new Reservation();
        reservation.setCustomerId(customerId);
        reservation.setScooterId(scooterId);
        reservation.setRideStatus(RideStatus.PENDING);

        if (scooter == null) {
            reservation.setRideStatus(RideStatus.INCORRECT_SCOOTER_ID);
//            return "Incorrect Scooter ID.";
        } else if(scooter.getReserved()) {
            reservation.setRideStatus(RideStatus.NOT_AVAILABLE);
        }else{
            RidingSession ridingSession= new RidingSession(scooter, customer);
            ridingSession.setRideStatus(RideStatus.RESERVED);

            ridingSession=ridingSessionRepository.save(ridingSession);
            reservation.setReservationId(ridingSession.getId());
            reservation.setRideStatus(RideStatus.RESERVED);
        }

        return reservation;


    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findOne(customerId);
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

    public Reservation updateReserve(Reservation reservation) {
        RidingSession ridingSession= ridingSessionRepository.findOne(reservation.getReservationId());

        ridingSession.setRideStatus(reservation.getRideStatus());
        if(reservation.getReservationStatus().equals(RideStatus.STARTED)) {
            ridingSession.setStartTime(Calendar.getInstance().getTime());
        }else  if(reservation.getReservationStatus().equals(RideStatus.ENDED)) {
            Date endTime=Calendar.getInstance().getTime();
            Date startTime=ridingSession.getStartTime();
            long totalTimeInMillis=endTime.getTime() - startTime.getTime();
            Double charge = unitPrice * totalTimeInMillis;
            ridingSession.setEndTime(endTime);
            ridingSession.setCharge(charge);
            ridingSessionRepository.save(ridingSession);
            reservation.setTotalCharge(charge);
        }

        return  reservation;

    }
}
