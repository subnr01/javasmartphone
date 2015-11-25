package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.RestaurantInfo;
import com.nihar.java_assignment.foureverhungry.local.model.SearchInfo;

import java.util.ArrayList;
import java.util.List;

public class ListingsPage extends Activity implements View.OnClickListener{
    private ArrayList<RestaurantInfo> searchResults;
    private SearchInfo searchInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings_page);
        searchResults = (ArrayList<RestaurantInfo>) getIntent().getExtras().getSerializable("listings");
        searchInfo = (SearchInfo) getIntent().getExtras().getSerializable("searchInfo");

        if (searchResults == null) {
            return;
        }

        LinearLayout layout = (LinearLayout) findViewById(R.id.listingsParentLayout);
        layout.removeAllViews();
        layout.setOrientation(LinearLayout.VERTICAL);
        Log.d("LINEAR LAYOUT", "SETTING LAYOUT PARAMS FOR " + searchResults.size());
        layout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < searchResults.size(); i++) {

            Button btn = new Button(this);
            RestaurantInfo res = searchResults.get(i);
            //btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText(res.getRestaurantName());
            byte[] data = res.getImage();
            /* This got hit once when the app did not respond for a long time. probably a network latency or some other issue.
            seen more than once, but reproducible rarely
             */
            if (data == null) {
                continue;
            }
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable drawImg = new BitmapDrawable(getResources(), bmp);

            data = res.getRatingImg();
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable drawRatingImg = new BitmapDrawable(getResources(), bmp);

            //draw.setBounds();
            btn.setCompoundDrawablesWithIntrinsicBounds(drawImg, null, drawRatingImg, null);
           // btn.clic

            btn.setOnClickListener(this);

            // use a macro instead.
            btn.setHeight(200);
            btn.setId(i);
            layout.addView(btn);
        }


    }

    public void goToDetailedView(View sender) {
        Intent intent = new Intent(ListingsPage.this, DetailedView.class);
        startActivity(intent);
        //finish();
    }

    public void goToMapView(View sender) {
        /* One aoption is to send only the restaurant names and locations. But if the map view needs
         * navigation to the detailed listings page it is better to send the entire listings structure.
         */

        Bundle bundle = new Bundle();
        bundle.putSerializable("listings", searchResults);
        bundle.putSerializable("searchInfo", searchInfo);

        Intent intent = new Intent(ListingsPage.this, MapPage.class);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.d("LISTINGS PAGE", "MAP PAGE STARTED");
        //finish();
        Log.d("LISTINGS PAGE", "MAP PAGE FINISHED");

    }

    public void saveSearch(View sender) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setMessage("Search saved");
        alertDialog.show();
        return;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId < searchResults.size()) {
            Log.d("LISTINGSPAGE", "RESTAURANT LINK CLICKED ID: " + viewId);
            RestaurantInfo res = searchResults.get(viewId);
            Bundle bundle = new Bundle();
            bundle.putSerializable("restaurant", res);
            Intent intent = new Intent(ListingsPage.this, DetailedView.class);
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();
        }
    }
}
