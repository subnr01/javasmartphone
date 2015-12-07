package edu.cmu.foreverhungry.services.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.foreverhungry.adapter.connect;
import edu.cmu.foreverhungry.adapter.readFromDatabase;
import edu.cmu.foreverhungry.adapter.writeToDatabase;
import edu.cmu.foreverhungry.model.LocationInfo;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.SearchInfo;

/**
 * Created by admin on 11/26/15.
 */
public class databaseConnector extends SQLiteOpenHelper implements connect, readFromDatabase, writeToDatabase {

    // Database Version
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "4EverHungryDB";
    private static final String TABLE_NAME = "Saved_Searches";

    public databaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("database connector", "CAME TO databaseConnector");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TAG = "onCreate";

        Log.d(TAG, "CAME TO ONCREATE_SQL");
        String CREATE_STUDENTSCORES_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                "EmailID varchar(255) NOT NULL," +
                "SavedName varchar(255) NOT NULL," +
                "Location String NOT NULL," +
                "Latitude Double," +
                "Longitude Double," +
                "Cuisine varchar(255) NOT NULL," +
                "Distance Double NOT NULL," +
                "PRIMARY KEY (EmailID, SavedName)" +
                ")";

        db.execSQL(CREATE_STUDENTSCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DATABASE CONNECTOR", "CAME TO onUpgrade");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public List<String> getSavedSearchesOfUser(String emailID) {
        Cursor c;
        List<String> searches = new ArrayList<String>();
        String query = "SELECT  DISTINCT SavedName FROM " + TABLE_NAME + " WHERE EmailID='" + emailID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        c = db.rawQuery(query, null);
        if (c == null) {
            Log.d("DATABASE CONNECTOR", "NO INFO FOUND IN DB FOR EmailID" + emailID);
            return null;
        }

        while (c.moveToNext()) {
            int searchNameIndex = c.getColumnIndex("SavedName");
            Log.d("DATABASE CONNECTOR", "NAME is stored in column index " + searchNameIndex);

            int rows = c.getCount();
            int cols = c.getColumnCount();
            Log.d("Rows", Integer.toString(rows));
            Log.d("Cols", Integer.toString(cols));
            searches.add(c.getString(searchNameIndex));
        }
        return searches;
    }

    public SearchInfo getListingsOfSavedSearch(String emailID, String searchName) {
        Log.d("DATABASE CONNECTOR", "CAME TO getListingsOfSavedSearch");

        Cursor c;
        List<RestaurantInfo> resList = new ArrayList<RestaurantInfo>();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE EmailID='" + emailID + "' AND SavedName = '" + searchName +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        c = db.rawQuery(query, null);
        if (c == null) {
            Log.d("DATABASE CONNECTOR", "NO INFO FOUND IN DB FOR EmailID" + emailID + " and name " + searchName);
            return null;
        }
        int searchNameIndex = c.getColumnIndex("SavedName");
        SearchInfo info = null;
        c.moveToFirst();
        if (c != null) {
            String location = c.getString(c.getColumnIndex("Location"));
            Double latitude = c.getDouble(c.getColumnIndex("Latitude"));
            Double longitude = c.getDouble(c.getColumnIndex("Longitude"));
            String cuisine = c.getString(c.getColumnIndex("Cuisine"));
            Double distance = c.getDouble(c.getColumnIndex("Distance"));
            info =  new SearchInfo(location, cuisine, distance, latitude, longitude);
        }
        return info;
    }

    public boolean saveSearch(String searchName, String emailID, SearchInfo info) {
        Log.d("DATABASE CONNECTOR", "CAME TO saveSearch");
        Log.d("SearchName", searchName);
        Log.d("emailID", emailID);
        Log.d("Location", info.getLocation());
        Log.d("Latitude", String.valueOf(info.getLatitude()));
        Log.d("Longitude", String.valueOf(info.getLongitude()));
        Log.d("Cuisine", info.getCuisine());
        Log.d("Distance", String.valueOf(info.getDistance()));

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("EmailID", emailID);
        values.put("SavedName", searchName);
        values.put("Location", info.getLocation());
        values.put("Latitude", info.getLatitude());
        values.put("Longitude", info.getLongitude());
        values.put("Cuisine", info.getCuisine());
        values.put("Distance", info.getDistance());
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    @Override
    public void deleteSearch(String searchName, String emailID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "SavedName = '" + searchName + "' AND EmailID = '" + emailID + "'", null);
        return;
    }

}
