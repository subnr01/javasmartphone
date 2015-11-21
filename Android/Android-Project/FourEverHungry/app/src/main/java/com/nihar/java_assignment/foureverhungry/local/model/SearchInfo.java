package com.nihar.java_assignment.foureverhungry.local.model;

/**
 * Created by Sudhir Ravi on 11/14/15.
 */
public class SearchInfo extends RestaurantInfo {
    private String location;
    private String cuisine;
    private double distance;

    public SearchInfo(String location, String cuisine, double distance) {
        this.location = location;
        this.cuisine = cuisine;
        this.distance = distance;
    }

    public String getLocation() {
        return location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public double getDistance() {
        return distance;
    }
}
