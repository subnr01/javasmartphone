package edu.cmu.foreverhungry.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.SearchInfo;
import edu.cmu.foreverhungry.model.UserInfo;
import edu.cmu.foreverhungry.services.database.databaseConnector;
import edu.cmu.foreverhungry.ui.login.ChangePassword;
import edu.cmu.foreverhungry.ui.login.LoginScreenActivity;
import edu.cmu.foreverhungry.ui.restaurant.ListingsPage;


public class SavedSearchesPage extends Activity implements View.OnClickListener {
    private databaseConnector db;
    private Spinner searchSelected;
    private static final String noSavedString = "NO SAVED SEARCHES FOUND";
    private Button ViewButton;
    private Button DeleteButton;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Saved Search Page", "ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_searches_page);
        ViewButton = (Button)findViewById(R.id.ViewButton);
        ViewButton.setOnClickListener(this);
        DeleteButton = (Button)findViewById(R.id.DeleteButton);
        DeleteButton.setOnClickListener(this);

        searchSelected = (Spinner) findViewById(R.id.spinnerSavedSearches);
        db = new databaseConnector(this);
        username = UserInfo.getInstance().getUsername();
        if (username == null) {
            Log.d("SAVED SEARCH PAGE ERR", "USERNAME IS NULL");
        }
        createSavedSearchesDropDown();

    }

    private void createSavedSearchesDropDown() {

        List<String> savedResults = db.getSavedSearchesOfUser(username);
        ArrayAdapter<String> adapter = null;
        if (savedResults == null || (savedResults.size() == 0)) {
            //return;
            Log.d("Saved Search Page", "NO SAVED SEARCH for user " + username);

            List<String> emptyResult = new ArrayList<>();
            emptyResult.add(noSavedString);
            adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, emptyResult);
        } else {
            Log.d("Saved Search Page", "SAVED SEARCH with length " + savedResults.size());

            adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, savedResults);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinnerSavedSearches);
        if (sItems != null) {
            sItems.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("Saved Search Page", "SOME BUTTON CLICKED");

        String searchResultSelected = searchSelected.getSelectedItem().toString();
        if (searchResultSelected == noSavedString) {
            Toast.makeText(getApplicationContext(), "There are no saved searches",
                    Toast.LENGTH_SHORT).show();
            createSavedSearchesDropDown();
            return;
        }
        switch (v.getId()) {
            case R.id.ViewButton:
                Log.d("Saved Search Page", "VIEW BUTTON CLICKED");
                SearchInfo savedInfo = db.getListingsOfSavedSearch(username, searchResultSelected);
                startListingActivity(savedInfo.getLocation(), savedInfo.getCuisine(), savedInfo.getDistance(),
                            savedInfo.getLatitude(), savedInfo.getLongitude());

                break;

            case R.id.DeleteButton:
                Log.d("Saved Search Page", "DELETE BUTTON CLICKED");

                db.deleteSearch(searchResultSelected, username);
                Toast.makeText(getApplicationContext(), "Deleted the search result " + searchResultSelected,
                        Toast.LENGTH_SHORT).show();
                createSavedSearchesDropDown();
                break;
        }

    }
    private void startListingActivity(String location, String cuisine, double distance, double lat, double lon) {

        Intent intent = new Intent(this, ListingsPage.class);
        String dist = String.valueOf(distance);
        intent.putExtra("location", location);
        intent.putExtra("cuisine", cuisine);
        intent.putExtra("distance", String.valueOf(distance));
        intent.putExtra("username", username);

        Log.v("SAVED SEARCHES", "LOAD LISTINGS PAGE FROM PREVIOUS SEARCH");
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            UserInfo.clearUserInfo();
            // signs user out, sends them to the login page
            Intent intent = new Intent(this, LoginScreenActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else if ( id == R.id.change_password) {
            Intent intent= new Intent(this, ChangePassword.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
