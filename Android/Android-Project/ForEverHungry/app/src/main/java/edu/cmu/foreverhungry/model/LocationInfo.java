package edu.cmu.foreverhungry.model;

import java.io.Serializable;

/**
 * Created by Nihar on 11/21/2015.
 */
public class LocationInfo implements Serializable{
    double Latitude;
    double Longitude;

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLatitude(double lat) {
        Latitude = lat;
    }

    public void setLongitude(double lon) {
        Longitude = lon;
    }
}
