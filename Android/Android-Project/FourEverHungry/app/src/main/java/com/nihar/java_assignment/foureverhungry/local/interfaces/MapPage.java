package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.RestaurantInfo;

import java.util.ArrayList;

public class MapPage extends Activity implements View.OnClickListener{
    private MapsActivity mymap;
    private ArrayList<RestaurantInfo> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        searchResults = (ArrayList<RestaurantInfo>) getIntent().getExtras().getSerializable("listings");
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.restaurantMap);
        mymap = new MapsActivity(searchResults, getApplicationContext());
        if (mymap == null) {
            Log.d("MAPS PAGE", "NULL MAP OBJECT");
            return;
        }
        mapFragment.getMapAsync(mymap);
    }

    @Override
    public void onClick(View v) {

    }
}
