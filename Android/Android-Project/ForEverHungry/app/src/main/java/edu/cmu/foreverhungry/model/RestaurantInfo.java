package edu.cmu.foreverhungry.model;

import android.media.Image;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import edu.cmu.foreverhungry.services.util.*;


/**
 * Created by Sudhir Ravi on 11/15/15.
 */
public class RestaurantInfo implements Serializable{
    private String name;
    private byte[] image;
    private String phone;
    private String address;
    private double rating;
    private byte[] ratingImg;
    private ArrayList<Review> reviews;
    private LocationInfo location;
    private String image_url;
    private String ratingImgURL;
    private String resID;

    public RestaurantInfo() {
        reviews = new ArrayList<Review>();
        location = new LocationInfo();
    }

    public RestaurantInfo(String name, String phone, String address, String url) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        reviews = new ArrayList<Review>();
        setImage(url);
    }

    public String getRestaurantName() {
        return name;
    }

    public void setRestaurantName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setImage(String url) {
        this.image = ImageReader.readImage(url);
    }

    public void setResID (String id) { this.resID = id;}

    public void setImageRaw(byte[] imageData) {
        this.image = imageData;
    }

    public void setImageURL(String url) {
        this.image_url = url;
    }
    public void setRatingImageRaw(byte[] imageRatingData) {
        this.ratingImg = imageRatingData;
    }

    public byte[] getImage() {
        return image;
    }

    public String getImageURL() {
        return image_url;
    }


    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public String getResID() {return resID;}

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setRatingImg(String url) {
        this.ratingImg = ImageReader.readImage(url);
    }

    public byte[] getRatingImg() {
        return ratingImg;
    }


    public void setRatingImgURL(String url) {
        this.ratingImgURL = url;
    }

    public String getRatingImgURL() {
        return ratingImgURL;
    }



    public void addReview(Review review) {
        reviews.add(review);
    }

    public void setLocation(double latitude, double longitude) {
        location.setLatitude(latitude);
        location.setLongitude(longitude);
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
}
