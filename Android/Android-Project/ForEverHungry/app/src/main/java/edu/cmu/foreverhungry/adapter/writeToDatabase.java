package edu.cmu.foreverhungry.adapter;

import android.location.Location;

import java.util.List;

import edu.cmu.foreverhungry.model.LocationInfo;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.SearchInfo;

/**
 * Created by admin on 11/26/15.
 */
public interface writeToDatabase {
    public boolean saveSearch(String searchName, String emailID, SearchInfo info);
    public void deleteSearch(String searchName, String emailID);
}
