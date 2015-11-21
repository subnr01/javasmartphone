package com.nihar.java_assignment.foureverhungry.local.model;

import android.util.Log;

import com.nihar.java_assignment.foureverhungry.remote.util.ImageReader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

/**
 * Created by Sudhir Ravi on 11/15/15.
 */
public class Review {
    private byte[] image;
    private String comment;

    public Review( String comment, String url) {
        this.comment = comment;
        this.image = ImageReader.readImage(url);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public byte[] getImage(){
        return image;
    }

    public void setImage(String url) {
        this.image = ImageReader.readImage(url);
    }
}
