package com.ridecell.scooter.model;

/**
 * Created by deepakkumar on 5/7/19.
 */

import lombok.Data;

@Data
public class Reservation {
    private Long reservationId;
    private String reservationStatus;
    private Long scooterId,customerId;
    private RideStatus rideStatus;
    private Double totalCharge;
}
