package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.RestaurantInfo;
import com.nihar.java_assignment.foureverhungry.local.model.SearchInfo;
import com.nihar.java_assignment.foureverhungry.remote.yelp.SearchUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchPage extends Activity {
    private HashMap<String, Double> distanceMappings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        createDistanceMap();
        createDistanceDropDown();
    }

    public void goToListings(View sender) {
        String location, cuisine, distanceQuery;
        double distance;
        ArrayList<RestaurantInfo> searchResults = null;

        EditText inputLocation = (EditText)findViewById(R.id.inputLocation);
        EditText inputCuisine = (EditText)findViewById(R.id.inputCuisine);
        Spinner inputDistance = (Spinner)findViewById(R.id.inputDistance);


        location = inputLocation.getText().toString();
        if(location.equals("Current")) {
            // TODO: Define current by obtaining current lat & long
        }
        cuisine = inputCuisine.getText().toString();
        distanceQuery = inputDistance.getSelectedItem().toString();
        Log.d("SEARCH PAGE", "DISTANCE QUERY " + distanceQuery);
        distance = getDistance(distanceQuery);
        location = "5506 Fifth Avenue Pittsburgh";
        /* The following hardcoded values can be used for debugging
        cuisine = "Thai";
        distance = 10000; // 10KM
        location = "Pittsburgh";
        */
        SearchInfo searchInfo = new SearchInfo(location, cuisine, distance);
        SearchUtil searchUtil = new SearchUtil(searchInfo, this.getApplicationContext());
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            searchResults = searchUtil.search(); // Invoke YELP web service with this information.

        }

        /*
        TODO: Add a check for whether we got any results at all. One could just throw a toast back
        to the user that this search did not yield any results.
         */
        Bundle bundle = new Bundle();
        bundle.putSerializable("listings", searchResults);
        Intent intent = new Intent(SearchPage.this, ListingsPage.class);
        intent.putExtras(bundle);
        startActivity(intent);
        //finish();
    }

    private double getDistance(String key) {

        return (double)distanceMappings.get(key);
    }

    private void createDistanceMap() {
        distanceMappings = new HashMap();
        distanceMappings.put("Bird's-eye View", 50.0); //50 m
        distanceMappings.put("Driving", 25000.0); // 25 km
        distanceMappings.put("Biking", 5000.0); // 5km
        distanceMappings.put("Walking", 2000.0); //2 km
        distanceMappings.put("Within 4 blocks", 300.0); //300m
    }

    private void createDistanceDropDown() {
        List<String> spinnerArray =  new ArrayList<String>();
        Iterator it = distanceMappings.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Float> pair = (Map.Entry)it.next();
            spinnerArray.add(pair.getKey());
        }
        //spinnerArray.add("item1");
        //spinnerArray.add("item2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.inputDistance);
        sItems.setAdapter(adapter);
    }

}
