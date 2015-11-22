package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.RestaurantInfo;
import com.nihar.java_assignment.foureverhungry.local.model.SearchInfo;
import com.nihar.java_assignment.foureverhungry.remote.yelp.SearchUtil;

import java.util.ArrayList;

public class SearchPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
    }

    public void goToListings(View sender) {
        String location, cuisine;
        double distance;
        ArrayList<RestaurantInfo> searchResults = null;

        EditText et = (EditText)findViewById(R.id.editText);
        location = et.getText().toString();
        if(location.equals("Current")) {
            // TODO: Define current by obtaining current lat & long
        }
        location = "Pittsburgh";
        et = (EditText)findViewById(R.id.editText); // TODO: This is currently a spinner and should be changed.
        cuisine = "Thai";
        distance = 10000; // 10KM
        SearchInfo searchInfo = new SearchInfo(location, cuisine, distance);
        SearchUtil searchUtil = new SearchUtil(searchInfo);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            searchResults = searchUtil.search(); // Invoke YELP web service with this information.

            //your codes here

        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("listings", searchResults);
        Intent intent = new Intent(SearchPage.this, ListingsPage.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

}
