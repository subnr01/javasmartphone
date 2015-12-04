package edu.cmu.foreverhungry.ui.search;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.*;
import edu.cmu.foreverhungry.services.yelpapi.SearchUtil;
import edu.cmu.foreverhungry.ui.restaurant.*;
import android.app.Activity;


/*

CURRENTLY THIS CLASS IS NOT USED, NEED
TO REMOVE IN FINAL


 */

public class SearchPage extends Activity {
    private HashMap<String, Double> distanceMappings;
    private double latitude;
    private double longitude;
    final static private int minTime = 0; // in ms
    final static private int minDistance = 0;// in meters
    volatile boolean foundLocation;
    private RelativeLayout WallPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        WallPaper = (RelativeLayout) findViewById(R.id.search_page);
        randomImage();
        createDistanceMap();
        createDistanceDropDown();
        findLocationAsyncTask findLoc = new findLocationAsyncTask();
        findLoc.execute();
        foundLocation = false;
        //foundLocation = true;
    }

    public void goToListings(View sender) {
        String location, cuisine, distanceQuery;
        double distance;

        ArrayList<RestaurantInfo> searchResults = null;

        EditText inputLocation = (EditText)findViewById(R.id.inputLocation);
        EditText inputCuisine = (EditText)findViewById(R.id.inputCuisine);
        Spinner inputDistance = (Spinner)findViewById(R.id.inputDistance);
        LocationManager service;
        service = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        location = inputLocation.getText().toString();
        if(location.equals("Current")) {

            // check if enabled and if not send user to the GPS settings
            // Better solution would be to display a dialog and suggesting to
            // go to the settings
            if (!enabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                makeToast("Enable GPS to use your current location");
                startActivity(intent);
                return;
            }
            else {
                try {
                    Log.d("SEARCH PAGE MAIN CLASS", "SLEEPING");

                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    Log.d("SEARCH PAGE", "SLEEP GOT INTERRUPTED");
                    e.printStackTrace();
                }
            }
            location = null;
            Log.d("Latitude is ", String.valueOf(latitude));
            Log.d("Latitude is ", String.valueOf(longitude));
            while (foundLocation == false) {

            }

        }
        cuisine = inputCuisine.getText().toString();
        distanceQuery = inputDistance.getSelectedItem().toString();
        Log.d("SEARCH PAGE", "DISTANCE QUERY " + distanceQuery);
        distance = getDistance(distanceQuery);
        //distance = 10000; // 10KM
        //location = "Pittsburgh";
        //location = "5506 Fifth Avenue Pittsburgh";
        /* The following hardcoded values can be used for debugging
        cuisine = "Thai";
        distance = 10000; // 10KM
        location = "Pittsburgh";
        */
        SearchInfo searchInfo = new SearchInfo(location, cuisine, distance, latitude, longitude);
        SearchUtil searchUtil = new SearchUtil(searchInfo, this.getApplicationContext());
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            searchResults = searchUtil.search(); // Invoke YELP web service with this information.

        }

        /*
        TODO: Add a check for whether we got any results at all. One could just throw a toast back
        to the user that this search did not yield any results.
         */
        Bundle bundle = new Bundle();
        bundle.putSerializable("listings", searchResults);
        bundle.putSerializable("searchInfo", searchInfo);

        Intent intent = new Intent(SearchPage.this, Listings.class);
        intent.putExtras(bundle);
        startActivity(intent);
        //finish();
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
        Spinner sItems = (Spinner) findViewById(R.id.inputDistance);
        sItems.setAdapter(adapter);
    }

    private void makeToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private class findLocationAsyncTask extends AsyncTask<Integer, Integer, Long> implements LocationListener {
        private LocationManager locationManager;

        protected findLocationAsyncTask() {
            Log.d("SEARCH PAGE", "findLocationAsyncTask CONSTRUCTOR");
        }
        @Override
        protected void onPreExecute() {
            Log.d("SEARCH PAGE", "ON PRE EXECUTE");

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String provider;
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            provider = locationManager.getBestProvider(criteria, false);
            /*
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("SEARCH PAGE", "NO PERMISSION FOR GPS");

                return;
            }
            */
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, this);
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, minTime, minDistance, this);


        }
        @Override
        /* Value returned by this guy is given to postExecute */
        protected Long doInBackground(Integer... nums) {
            Log.d("SEARCH PAGE", "DO IN BACKGROUND");

            while (latitude == 0.0 && longitude == 0.0) {

            }
            return Long.valueOf("0");
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);

        }
        @Override
        protected void onPostExecute(Long result) {
            Log.d("SEARCH PAGE", "ON POST EXECUTE" + " Lat : " + latitude + " Long : + " + longitude);
            //showDialog("Downloaded " + result + " bytes");
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.d("SEARCH PAGE", "ON LOCATION CHANGED");
            if (foundLocation == false) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                foundLocation = true;
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (status == LocationProvider.AVAILABLE) {
                Log.d("SEARCH PAGE", "ON STATUS CHANGED - AVAILABLE");
            }
            else if (status == LocationProvider.OUT_OF_SERVICE) {
                Log.d("SEARCH PAGE", "ON STATUS CHANGED - OUT OF SERVICE");

            }
            else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
                Log.d("SEARCH PAGE", "ON STATUS CHANGED - TEMP UNAVAILABLE");
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


    }

    private void randomImage() {
        Random rand = new Random();
        int random = rand.nextInt(10);
        int toLoad = 0;
        switch (random) {
            case 0:
                toLoad = R.drawable.chinese;
                break;
            case 1:
                toLoad = R.drawable.thai;
                break;
            case 2:
                toLoad = R.drawable.italian;
                break;
            case 3:
                toLoad = R.drawable.mexican;
                break;
            case 4:
                toLoad = R.drawable.mediterranean;
                break;
            case 5:
                toLoad = R.drawable.indian;
                break;
            case 6:
                toLoad = R.drawable.japanese;
                break;
            case 7:
                toLoad = R.drawable.korean;
                break;
            case 8:
                toLoad = R.drawable.burger;
                break;
            case 9:
                toLoad = R.drawable.arabian;
                break;

        }


        WallPaper.setBackgroundResource(toLoad);

    }

}



