package edu.cmu.foreverhungry.ui.search;
/**
 * Created by subs on 11/25/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

import android.os.Bundle;
import edu.cmu.foreverhungry.R;

import edu.cmu.foreverhungry.model.UserInfo;
import edu.cmu.foreverhungry.ui.login.ChangePassword;
import edu.cmu.foreverhungry.ui.login.LoginScreenActivity;
import edu.cmu.foreverhungry.ui.restaurant.ListingsPage;

import android.content.Context;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.Menu;
import android.location.LocationManager;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class SearchOptionsPage extends Activity
                        implements OnClickListener{

    public static final String TAG = "SearchOptionsPage";
    private HashMap<String, Double> distanceMappings;
    private RelativeLayout WallPaper;

    Button show_search;
    EditText inputLocation;
    EditText inputCuisine;
    String locationInput;
    Spinner inputDistance;
    String cuisineInput ;
    String distanceQuery ;
    private double latitude;
    private double longitude;
    private double distance;
    private String username;

    private LocationManager locationManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options_page);
        username = UserInfo.getInstance().getUsername();

        WallPaper = (RelativeLayout) findViewById(R.id.search_options_layout);

        show_search = (Button) findViewById(R.id.start_search);
        inputLocation = (EditText)findViewById(R.id.locationInput);
        inputCuisine = (EditText)findViewById(R.id.cuisineInput);
        inputDistance = (Spinner) findViewById(R.id.distanceInput);
        createDistanceMap();
        createDistanceDropDown();
        randomImage();
        show_search.setOnClickListener(this);

    }

    @Override
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

    @Override
    public void onClick(View v) {
        Log.v(TAG, "saved");
        locationInput = inputLocation.getText().toString();
        cuisineInput = inputCuisine.getText().toString();
        distanceQuery = inputDistance.getSelectedItem().toString();

        Log.v(TAG, locationInput);
        Log.v(TAG,cuisineInput);
        Log.v(TAG, distanceQuery);

        if (locationInput == null ||
                cuisineInput == null ||
                distanceQuery == null) {

            Toast.makeText(getApplicationContext(), "Some fields are empty",
                                            Toast.LENGTH_LONG).show();
            return;

        }

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            Toast.makeText(getApplicationContext(), "Enable GPS to use your current location",
                    Toast.LENGTH_LONG).show();
            startActivity(intent);
            return;
        }

        startListingActivity();

    }


    private void createDistanceDropDown() {
        List<String> spinnerArray =  new ArrayList<String>();
        Iterator it = distanceMappings.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Float> pair = (Map.Entry)it.next();
            spinnerArray.add(pair.getKey());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.distanceInput);
        sItems.setAdapter(adapter);
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


    private void startListingActivity() {

        Intent intent = new Intent(this, ListingsPage.class);
        distance = getDistance(distanceQuery);
        String dist = String.valueOf(distance);

        intent.putExtra("location", locationInput);
        intent.putExtra("cuisine", cuisineInput);
        intent.putExtra("distance", dist);

        Log.v(TAG, "All set to display resuarant information");
        startActivity(intent);

    }


    private void randomImage() {
        Random rand = new Random();
        int random = rand.nextInt(10);
        int toLoad = 0;
        switch (random) {
            case 0:
                toLoad = R.drawable.img1;
                break;
            case 1:
                toLoad = R.drawable.img2;
                break;
            case 2:
                toLoad = R.drawable.img3;
                break;
            case 3:
                toLoad = R.drawable.img4;
                break;
            case 4:
                toLoad = R.drawable.img5;
                break;
            case 5:
                toLoad = R.drawable.img6;
                break;
            case 6:
                toLoad = R.drawable.img7;
                break;
            case 7:
                toLoad = R.drawable.img8;
                break;
            case 8:
                toLoad = R.drawable.img9;
                break;
            case 9:
                toLoad = R.drawable.img10;
                break;

        }

        WallPaper.setBackgroundResource(toLoad);

    }



}


