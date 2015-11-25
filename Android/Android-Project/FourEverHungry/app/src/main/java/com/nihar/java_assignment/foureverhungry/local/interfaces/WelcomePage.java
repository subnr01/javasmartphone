package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nihar.java_assignment.foureverhungry.R;

public class WelcomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSearchPage(View sender) {
        Intent intent = new Intent(WelcomePage.this, SearchPage.class);
        startActivity(intent);
        //finish();
    }
    public void goToSavedSearches(View sender) {
        Intent intent = new Intent(WelcomePage.this, SavedSearchesPage.class);
        startActivity(intent);
        //finish();
    }

    }
