package edu.cmu.foreverhungry.adapter;

import java.util.List;

import edu.cmu.foreverhungry.model.LocationInfo;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.SearchInfo;

/**
 * Created by admin on 11/26/15.
 */
public interface readFromDatabase {
    public List<String> getSavedSearchesOfUser(String emailID);
    public SearchInfo getListingsOfSavedSearch(String emailID, String searchName);

}
