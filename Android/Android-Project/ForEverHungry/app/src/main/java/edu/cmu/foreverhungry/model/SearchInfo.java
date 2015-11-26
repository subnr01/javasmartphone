package edu.cmu.foreverhungry.model;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Sudhir Ravi on 11/14/15.
 */
public class SearchInfo extends RestaurantInfo implements Serializable{
    private String location;
    private String cuisine;
    private double distance;
    private LocationInfo locInfo;

    public SearchInfo(String location, String cuisine, double distance, double lat, double lon) {
        this.location = location;
        this.cuisine = cuisine;
        this.distance = distance;
        locInfo = new LocationInfo();
        locInfo.setLatitude(lat);
        locInfo.setLongitude(lon);
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

    public LocationInfo getLocationInfo() {
        return locInfo;
    }

    public double getLatitude() {
        Log.d("Searchinfo", "get latitude");
        return locInfo.getLatitude();
    }

    public double getLongitude() { return locInfo.getLongitude();}

}
