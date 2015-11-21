package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nihar.java_assignment.foureverhungry.R;

public class ListingsPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings_page);
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
