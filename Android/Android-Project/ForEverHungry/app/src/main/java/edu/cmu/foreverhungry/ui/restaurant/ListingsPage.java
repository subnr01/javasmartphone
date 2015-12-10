package edu.cmu.foreverhungry.ui.restaurant;
/**
 * Created by subs on 11/25/15.
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.SearchInfo;
import edu.cmu.foreverhungry.model.UserInfo;
import edu.cmu.foreverhungry.services.database.databaseConnector;

import android.text.InputType;
import android.util.Log;

import android.location.Location;
import android.location.LocationProvider;
import android.location.LocationManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.software.shell.fab.ActionButton;

import edu.cmu.foreverhungry.ui.login.ChangePassword;
import edu.cmu.foreverhungry.ui.login.LoginScreenActivity;
import edu.cmu.foreverhungry.ui.maps.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/*
  Android Activity to display restuarant info
 */


public class ListingsPage extends FragmentActivity {


    private static final String TAG = "ListingsPage";


    private String username;

    private String cuisineInput ;
    private String locationInput ;
    private double Latitude;
    private double Longitude;
    private double Distance;
    private databaseConnector db;

    private LocationManager locationManager;
    private boolean onLocationFound = false;
    private SearchInfo mObjects;
    private ArrayList<RestaurantInfo> searchResults;

    final static private int minTime = 0; // in ms
    final static private int minDistance = 0;// in meters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings_page);
        onLocationFound = false;
        db = new databaseConnector(this);

        /* Get the user input from the previous activity */
        String dist = getIntent().getStringExtra("distance");
        cuisineInput = getIntent().getStringExtra("cuisine");
        locationInput = getIntent().getStringExtra("location");
        username = getIntent().getStringExtra("username");
        //username = UserInfo.getInstance().getUsername();
        if (username == null) {
            Log.d("LISTINGS PAGE ERROR:", "USERNAME IS NULL");
        } else {
            Log.d("UserName is ", username);
        }
        /* converting back to double */
        Distance = Double.parseDouble(dist);
        mObjects = new SearchInfo();


        /* Setup the elevated button */
        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);
        actionButton.setButtonColor(getResources().getColor(R.color.fab_material_purple_500));
        actionButton.setButtonColorPressed(getResources().getColor(R.color.fab_material_purple_900));
        actionButton.setImageResource(R.drawable.fab_plus_icon);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSearch(ListingsPage.this);
            }
        });



        ActionButton mapViewButton = (ActionButton) findViewById(R.id.map_view_button);
        mapViewButton.setButtonColor(getResources().getColor(R.color.fab_material_purple_500));
        mapViewButton.setButtonColorPressed(getResources().getColor(R.color.fab_material_purple_900));
        mapViewButton.setImageSize(75);
        mapViewButton.setImageResource(R.drawable.google_map);
        mapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMapActivity();
            }
        });

        /* Setup location manager to obtain location information */
        initLocationManager();

    }


    /*
        Display the savesearch window to receive
        user input save search details
     */

    public void saveSearch(Context mContext)
    {
        Log.d("ListingsPage", "Came to SaveSearch");
        // Create dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Save Search");
        alert.setMessage("Please enter search name");

        // Set an EditText view to get user input
        final EditText input = new EditText(getApplicationContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE);



        // Set up dialog layout using FrameLayout as container for elements
        FrameLayout container = new FrameLayout(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                            (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 48;
        params.rightMargin = 48;
        params.topMargin = 48;
        params.bottomMargin = 16;
        input.setLayoutParams(params);
        container.addView(input);
        alert.setView(container);

        //Listener for the save button
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();

                if (value.length() > 5) {

                    //@TODO
                    //Update database with the search information.
                    boolean saveSearch;
                    saveSearch = saveSearchToDataBase(value);
                    if (saveSearch) {
                        Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Could not save the result!", Toast.LENGTH_SHORT).show();
                    }
                } else { // Send error toast if description is too short
                    Toast.makeText(getApplicationContext(), "Save FAILED!!! Minimum 5 letters needed",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });


        // Cancel button action
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        //Show the dialog box
        alert.show();


    }


    public void startMapActivity()
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("listings", searchResults);
        bundle.putSerializable("searchInfo", mObjects);
        Intent intent = new Intent(this, MapPage.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }

    public void initLocationManager()
    {
        Log.v("subbu1", "initLocationManagert");
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        if (locationInput.equalsIgnoreCase("current")) {
            Location location =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.v("subbu1", "Current selected");

        } else
        {
            //TODO
            //location to be obtained from some other service???
            loadListingsFragment();
        }

        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.v("subbu2", "ON LOCATION CHANGED");
                Latitude = location.getLatitude();
                Longitude = location.getLongitude();

                if (!onLocationFound){
                    Log.v("subbu1", "fragment reopened");
                    onLocationFound = true;
                    /*
                        Now that we have got the location information
                        we can move forward to obtain the restaurant info
                     */
                    loadListingsFragment();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                if (status == LocationProvider.AVAILABLE) {
                    Log.v("subbu1", "ON STATUS CHANGED - AVAILABLE");
                }
                else if (status == LocationProvider.OUT_OF_SERVICE) {
                    Log.v("subbu1", "ON STATUS CHANGED - OUT OF SERVICE");

                }
                else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
                    Log.v("subbu1", "ON STATUS CHANGED - TEMP UNAVAILABLE");
                }
            }


            @Override
            public void onProviderEnabled(String provider) {
                Log.d("SEARCH PAGE", "ON PROVIDER ENABLED");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("SEARCH PAGE", "ON PROVIDER DISABLED");
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener);

        //TODO get location from network provider
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, this);
        //locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, minTime, minDistance, this);



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
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    * Load the listings fragment
    */
    public void loadListingsFragment(){
        Log.v("subbu1","load listing fragment");


        Log.v(TAG,String.valueOf(Latitude));
        Log.v(TAG, String.valueOf(Longitude));
        Log.v(TAG,cuisineInput);
        Log.v(TAG, String.valueOf(Distance));


        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListingsFragment listingsFragment = new ListingsFragment();

        /*
        Send the user input info
        to the listing fragment
         */
        Bundle bundle = new Bundle();
        bundle.putString("cuisine",cuisineInput);
        bundle.putString("location",locationInput);
        bundle.putDouble("longitude", Longitude);
        bundle.putDouble("latitude",Latitude);
        bundle.putDouble("distance",Distance);

        Log.v("subbu1", String.valueOf(Latitude));
        Log.v("subbu1",String.valueOf(Longitude));

        listingsFragment.setArguments(bundle);

        Fragment mFragment = fragmentManager.
                findFragmentById(R.id.container);
        if ( mFragment == null) {
            fragmentTransaction.add(R.id.container,listingsFragment);
        }
        fragmentTransaction.commit();
    }

    public void updateSearchInfo (SearchInfo searchResults) {
        this.mObjects = searchResults;
    }

    public void returnSearchResults(ArrayList<RestaurantInfo> results)
    {
        searchResults = results;
    }

    private boolean saveSearchToDataBase(String searchName) {
        Log.d("saveSearchToDataBase", "SearchName is " + searchName);
        if (mObjects == null) {
            Log.d("saveSearchToDataBase", "Search Results Not Saved");
                return false;
        }
        db.saveSearch(searchName, username, mObjects);

        return true;
    }




}
