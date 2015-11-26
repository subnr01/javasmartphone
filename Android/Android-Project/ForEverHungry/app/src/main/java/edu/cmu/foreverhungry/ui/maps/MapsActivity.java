package edu.cmu.foreverhungry.ui.maps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import edu.cmu.foreverhungry.R;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.*;
import edu.cmu.foreverhungry.ui.restaurant.*;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ArrayList<RestaurantInfo> list;
    private Context context;

    public MapsActivity(ArrayList<RestaurantInfo> list, Context context) {
        Log.d("MAP ACTIVITY", "CONSTRUCTOR");

        this.list = list;

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("MAP ACTIVITY", "ON CREATE");
        //getActivity();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        /* Store handle for future reference */
        Log.d("MAP ACTIVITY", "MAP IS READY");

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        this.plotRestaurants(list);

    }

    public void plotRestaurants(ArrayList<RestaurantInfo> list) {

        if (mMap == null) {
            Log.d("MAP ACTIVITY", "MAP NOT READY YET");
            return;
        }
        int i = 0;
        if (list == null) {
            return;
        }
        LatLng restLocation;
        MarkerOptions markerOpt;
        Marker resMarker;
        RestaurantInfo res;
        double latitude;
        double longitude;
        String resName;

        for (i = 0; i < list.size(); i++) {
            res = list.get(i);
            latitude = res.getLatitude();
            longitude = res.getLongitude();
            resName = res.getRestaurantName();
            Log.d("MAPS ACTIVITY RES :" + i, "Lat: " + latitude + "Long: " + longitude + "Name: " + resName);
            restLocation = new LatLng(latitude, longitude);
            if (i == 0) {
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(restLocation));
                Log.d("MAPS ACTIVITY", "ZOOMING IN ");

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restLocation, 12));

            }
            /* Snippet is used to identify restaurant later when the user clicks on it */
            //markerOpt = new MarkerOptions().position(restLocation).title(resName).snippet(String.valueOf(i));
            markerOpt = new MarkerOptions().position(restLocation).title(resName);

            resMarker = mMap.addMarker(markerOpt);
            //resMarker.id
            //resMarker.showInfoWindow();

        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String markerId = marker.getId();
        Log.d("MAPS ACTIVITY", "CLICKED MARKER" + markerId);
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String markerId = marker.getId();
        Log.d("MAPS ACTIVITY", "CLICKED INFO WINDOW" + markerId);
        //int resId = Integer.valueOf(marker.getSnippet());
        //RestaurantInfo res = list.get(resId);
        //goToListingsPage(res);

        /* Go to detailed listings page with information about the restaurant that is selected */
    }

    private void goToListingsPage(RestaurantInfo res) {
        /* CANNOT MAKE THIS GO TO ACTIVITY BECAUSE OF RUN TIME CRASHES */
        Bundle bundle = new Bundle();

        bundle.putSerializable("restaurant", res);
        if (context == null) {
            Log.d("MAPS ACTIVITY", "NULL CONTEXT");
            return;
        }
        Intent intent = new Intent(context, DetailedView.class);
        if (intent == null) {
            Log.d("MAPS ACTIVITY", "NULL INTENT");
            return;
        }
        intent.putExtras(bundle);
        //this.getActivity().
        //startActivityFromFragment(this, intent);
        //finish();
    }
}
