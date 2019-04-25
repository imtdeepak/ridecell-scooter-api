package com.ridecell.scooter.utils;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

/**
 * Created by deepakkumar on 4/25/19.
 */
public class DistanceUtilTest {
    @Test
    public void distanceBetweenPoints() throws Exception {
        //Mountain House, CA
        double lat1=37.7827014;
        double long1=-121.5469659;
        //Tracy,CA
        double lat2=37.7397;
        double long2=-121.425;
        double distance = DistanceUtil.distanceBetweenPoints(lat1,long1,lat2,long2,'N');
        //Expected to be around 7 miles
        System.out.println("Distance =" + distance);
        Assert.assertTrue(distance>6);
    }

}