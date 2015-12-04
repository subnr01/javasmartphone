package edu.cmu.foreverhungry.ui.restaurant;
/**
 * Created by subs on 11/25/15.
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationListener;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import edu.cmu.foreverhungry.R;

import android.text.InputType;
import android.util.Log;

import android.location.Location;
import android.location.LocationProvider;
import android.location.LocationManager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.software.shell.fab.ActionButton;



/*
  Android Activity to display restuarant info
 */


public class ListingsPage extends FragmentActivity {


    private static final String TAG = "ListingsPage";



    private String cuisineInput ;
    private String locationInput ;
    private double Latitude;
    private double Longitude;
    private double Distance;

    private LocationManager locationManager;
    private boolean onLocationFound = false;

    final static private int minTime = 0; // in ms
    final static private int minDistance = 0;// in meters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings_page);
        onLocationFound = false;


        /* Get the user input from the previous activity */
        String dist = getIntent().getStringExtra("distance");
        cuisineInput = getIntent().getStringExtra("cuisine");
        locationInput = getIntent().getStringExtra("location");

        /* converting back to double */
        Distance = Double.parseDouble(dist);



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

        /* Setup location manager to obtain location information */
        initLocationManager();

    }


    /*
        Display the savesearch window to receive
        user input save search details
     */

    public void saveSearch(Context mContext)
    {
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
                    Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_SHORT).show();

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


    public void initLocationManager()
    {
        Log.v("subbu1", "initLocationManagert");
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        if (locationInput.equalsIgnoreCase("current")) {
            Location location =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else
        {
            //TODO
            //location to be obtained from some other service???
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


}
