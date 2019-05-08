package com.ridecell.scooter.entities;

import com.ridecell.scooter.model.RideStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;

/**
 * Created by deepakkumar on 5/7/19.
 */
@Entity
@Data
public class RidingSession implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="scooter_id")
    private Scooter scooterId;

    private Date startTime;

    private Date endTime;

    private RideStatus rideStatus;

    private Double charge;



    public RidingSession(Scooter scooterId, Customer customerId) {
        this.scooterId=scooterId;
        this.customerId=customerId;
    }
}
