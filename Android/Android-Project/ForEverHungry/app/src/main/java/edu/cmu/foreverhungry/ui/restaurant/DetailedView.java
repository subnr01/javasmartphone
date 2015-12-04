package edu.cmu.foreverhungry.ui.restaurant;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.cmu.foreverhungry.ui.restaurant.*;
import edu.cmu.foreverhungry.model.*;
import edu.cmu.foreverhungry.R;

import java.util.ArrayList;

public class DetailedView extends Activity {
    private RestaurantInfo res;
    private TextView resInfo;
    private ImageView img;
    private ImageView ratingImg;
    private ArrayList<Review> reviews;
    private LinearLayout reviewsOuterLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        res = (RestaurantInfo) getIntent().getExtras().getSerializable("restaurant");
        Log.d("DETAILED VIEW", "ON CREATE");

        resInfo = (TextView) findViewById(R.id.restaurantInfo);
        img = (ImageView) findViewById(R.id.resImage);
        ratingImg = (ImageView) findViewById(R.id.resRatingImage);

        byte[] data = res.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        //Drawable drawImg = new BitmapDrawable(getResources(), bmp);
        img.setImageBitmap(bmp);

        data = res.getRatingImg();
        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        //Drawable drawImg = new BitmapDrawable(getResources(), bmp);
        ratingImg.setImageBitmap(bmp);

        resInfo.setText(res.getRestaurantName() + "\n" + res.getAddress());

        reviewsOuterLayout = (LinearLayout) findViewById(R.id.outerReviewsLayout);
        reviewsOuterLayout.removeAllViews();
        reviewsOuterLayout.setOrientation(LinearLayout.VERTICAL);
        reviewsOuterLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        int i = 0;
        reviews = res.getReviews();
        Log.d("DETAILED VIEW", "NUM OF REVIEWS: " + reviews.size());

        for (i = 0; i < reviews.size(); i++) {
            Button btn = new Button(this);
            Review rev = reviews.get(i);
            Log.d("DETAILED VIEW", "REVIEW COMMENT " + rev.getComment());

            btn.setText(rev.getComment());

            byte[] binary = res.getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(binary, 0, binary.length);
            Drawable drawImg = new BitmapDrawable(getResources(), bm);
            btn.setCompoundDrawablesWithIntrinsicBounds(drawImg, null, null, null);
            btn.setClickable(false);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            reviewsOuterLayout.addView(btn);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }
}
