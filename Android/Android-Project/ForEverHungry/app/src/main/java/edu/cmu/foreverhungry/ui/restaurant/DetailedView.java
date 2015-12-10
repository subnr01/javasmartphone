package edu.cmu.foreverhungry.ui.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.cmu.foreverhungry.services.util.ImageReader;
import edu.cmu.foreverhungry.ui.login.*;
import edu.cmu.foreverhungry.ui.restaurant.*;
import edu.cmu.foreverhungry.model.*;
import edu.cmu.foreverhungry.R;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

import java.util.ArrayList;

public class DetailedView extends Activity implements View.OnClickListener {
    private RestaurantInfo res;
    private TextView resInfo;
    private ImageView img;
    private ImageView ratingImg;
    private ArrayList<Review> reviews;
    private LinearLayout reviewsOuterLayout;
    private ImageButton callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detailed_view);
        setContentView(R.layout.detailed_view_activity);

        res = (RestaurantInfo) getIntent().getExtras().getSerializable("restaurant");
        Log.d("DETAILED VIEW", "ON CREATE");

        resInfo = (TextView) findViewById(R.id.restaurantInfo);
        img = (ImageView) findViewById(R.id.resImage);
        ratingImg = (ImageView) findViewById(R.id.resRatingImage);
        callButton =(ImageButton) findViewById(R.id.call_image);

        callButton.setOnClickListener(this);

        if ( res.getImage() != null) {
            byte[] data = res.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            //Drawable drawImg = new BitmapDrawable(getResources(), bmp);
            img.setImageBitmap(bmp);
        }

        if (res.getRatingImg() != null) {
            byte[] ratingData = res.getRatingImg();
            Bitmap ratingBmp = BitmapFactory.decodeByteArray(ratingData, 0, ratingData.length);
            //Drawable drawImg = new BitmapDrawable(getResources(), bmp);
            ratingImg.setImageBitmap(ratingBmp);
        }

        resInfo.setText(res.getRestaurantName() + "\n" + res.getAddress());

        reviewsOuterLayout = (LinearLayout) findViewById(R.id.outerReviewsLayout);
        reviewsOuterLayout.removeAllViews();
        reviewsOuterLayout.setOrientation(LinearLayout.VERTICAL);
        reviewsOuterLayout.setLayoutParams(new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        int i = 0;
        reviews = res.getReviews();
        Log.d("DETAILED VIEW", "NUM OF REVIEWS: " + reviews.size());

        for (i = 0; i < reviews.size(); i++) {
            Button btn = new Button(this);
            Review rev = reviews.get(i);
            Log.d("DETAILED VIEW", "REVIEW COMMENT " + rev.getComment());

            btn.setText(rev.getComment());

            byte[] binary = res.getImage();
            if ( res.getImage() != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(binary, 0, binary.length);
                Drawable drawImg = new BitmapDrawable(getResources(), bm);
                btn.setCompoundDrawablesWithIntrinsicBounds(drawImg, null, null, null);
            }
            btn.setClickable(false);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));


            reviewsOuterLayout.addView(btn);
        }

        EndCallListener callListener = new EndCallListener();
        TelephonyManager callManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        callManager.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        String phNo = res.getPhone();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phNo));
        startActivity(callIntent);

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

    private class EndCallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if(TelephonyManager.CALL_STATE_RINGING == state) {
                Log.v("subbu1", "RINGING, number: " + incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
                //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
                Log.v("subbu1", "OFFHOOK");
            }
            if(TelephonyManager.CALL_STATE_IDLE == state) {
                //when this state occurs, and your flag is set, restart your app
                Log.v("subbu1", "IDLE");
            }
        }
    }
}
