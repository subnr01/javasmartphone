package edu.cmu.foreverhungry.ui.maps;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.*;

import com.google.android.gms.maps.MapFragment;


import java.util.ArrayList;

public class MapPage extends Activity implements View.OnClickListener{
    private MapsActivity mymap;
    private ArrayList<RestaurantInfo> searchResults;
    private SearchInfo searchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        searchResults = (ArrayList<RestaurantInfo>) getIntent().getExtras().getSerializable("listings");
        searchInfo = (SearchInfo) getIntent().getExtras().getSerializable("searchInfo");

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.restaurantMap);
        mymap = new MapsActivity(searchResults, getApplicationContext());
        if (mymap == null) {
            Log.d("MAPS PAGE", "NULL MAP OBJECT");
            return;
        }
        mapFragment.getMapAsync(mymap);

        populateSearchInfo();
    }

    /* Populate the search info to show to user */
    private void populateSearchInfo() {
        Log.d("MAPS PAGE", "IN POPULATE SEARCH");

        Button infoB = (Button)findViewById(R.id.searchInfoButton);

        if (infoB == null) {
            Log.d("MAPS PAGE", "NULL BUTTON");
            return;
        }

        // TODO: Use actual user name here once login info is added.
        StringBuffer infoMsg = new StringBuffer();

        Log.d("MAPS PAGE", "SET TEXT WORKED.");
        if (searchInfo.getLocation() != null) {
            infoMsg.append("LOCATION : " + searchInfo.getLocation() + "\n");
        } else {
            infoMsg.append("LOCATION : " + "CURRENT" + "\n");
            infoMsg.append("LATITUDE : " + searchInfo.getLatitude() + "\n");
            infoMsg.append("LONGITUDE : " + searchInfo.getLongitude() + "\n");
        }
        infoMsg.append("CUISINE : " + searchInfo.getCuisine() + "\n");
        infoMsg.append("DISTANCE : " + searchInfo.getDistance() + "m." + "\n");
        infoB.setTextSize(10);
        /* Make the button transparent */
        infoB.getBackground().setAlpha(0);
        infoB.setClickable(false);
        infoB.setText(infoMsg);
    }

    @Override
    public void onClick(View v) {

    }
}

