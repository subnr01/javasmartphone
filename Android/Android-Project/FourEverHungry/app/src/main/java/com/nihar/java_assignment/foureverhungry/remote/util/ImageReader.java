package com.nihar.java_assignment.foureverhungry.remote.util;

import android.util.Log;

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
public class ImageReader {
    public static byte[] readImage(String url) {
        try {
            Log.d("Image Reader :", url);

            URL imgURL = new URL(url);
            URLConnection con = imgURL.openConnection();

            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int current; int offset = 0;
            while ((current = bis.read()) != -1) {
                //Log.d("WRITE IMAGE OFFSET:", String.valueOf(offset));
                bos.write(ByteBuffer.allocate(1).put((byte)current).array(), 0, 1);
                offset++;
            }

            return bos.toByteArray();
        } catch (MalformedURLException e) {
            Log.d("ERROR:", "Invalid image URL; " + e.getMessage());
        } catch (IOException e) {
            Log.d("ERROR:", "Could not load image; " + e.getMessage());
        } catch (IndexOutOfBoundsException e){
            Log.d("ERROR:", "Could not write image; " + e.getMessage());
        }


        return null;
    }
}
