package edu.cmu.foreverhungry.model;

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
import edu.cmu.foreverhungry.services.util.*;

/**
 * Created by Sudhir Ravi on 11/15/15.
 */
public class Review implements Serializable{
    private byte[] image;
    private String comment;
    private String name;

    public Review( String name, String comment, String url) {
        this.name = name;
        this.comment = comment;
        this.image = ImageReader.readImage(url);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage(){
        return image;
    }

    public void setImage(String url) {
        this.image = ImageReader.readImage(url);
    }
}
