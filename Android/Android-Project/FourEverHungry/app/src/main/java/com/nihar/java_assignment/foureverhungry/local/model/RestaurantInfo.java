package com.nihar.java_assignment.foureverhungry.local.model;

import android.media.Image;
import android.util.Log;

import com.nihar.java_assignment.foureverhungry.remote.util.ImageReader;

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

    public RestaurantInfo() {
        reviews = new ArrayList<Review>();
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

    public byte[] getImage() {
        return image;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRatingURL(String url) {
        this.ratingImg = ImageReader.readImage(url);
    }

    public byte[] getRatingURL() {
        return ratingImg;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }
}