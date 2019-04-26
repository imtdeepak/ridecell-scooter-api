package com.ridecell.scooter.utils;

/**
 * Created by deepakkumar on 4/25/19.
 */
public class DistanceUtil {

    public static Double distanceBetweenPoints(double latitude1, double longitude1, double latitude2, double longitude2, char unit) {
        double distance = 0.0;

        double theta = longitude1 - longitude2;
        distance = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) + Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        if ('K' == unit) {
            distance = distance * 1.609344;
        } else if ('N' == unit) {
            distance = distance * 0.8684;
        }
        return distance;

    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
