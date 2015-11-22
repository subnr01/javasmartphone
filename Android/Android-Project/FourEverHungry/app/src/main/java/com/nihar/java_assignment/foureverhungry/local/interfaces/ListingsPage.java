package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.RestaurantInfo;

import java.util.ArrayList;

public class ListingsPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings_page);
        ArrayList<RestaurantInfo> searchResults = null;

        searchResults = (ArrayList<RestaurantInfo>) getIntent().getExtras().getSerializable("listings");

        LinearLayout layout = (LinearLayout) findViewById(R.id.listingsParentLayout);
        layout.removeAllViews();
        layout.setOrientation(LinearLayout.VERTICAL);
        Log.d("LINEAR LAYOUT", "SETTING LAYOUT PARAMS FOR " + searchResults.size());
        layout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < searchResults.size(); i++) {

            Button btn = new Button(this);

            //btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText(searchResults.get(i).getRestaurantName());

            btn.setId(i);
            layout.addView(btn);
        }


    }

    public void goToDetailedView(View sender) {
        Intent intent = new Intent(ListingsPage.this, DetailedView.class);
        startActivity(intent);
        finish();
    }

    public void goToMapView(View sender) {
        Intent intent = new Intent(ListingsPage.this, MapPage.class);
        startActivity(intent);
        finish();
    }

    public void saveSearch(View sender) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setMessage("Search saved");
        alertDialog.show();
        return;
    }
}
