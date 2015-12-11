package edu.cmu.foreverhungry.ui.restaurant;


import android.content.Context;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.cmu.foreverhungry.R;
import de.hdodenhof.circleimageview.CircleImageView;

import edu.cmu.foreverhungry.model.RestaurantInfo;


public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

    public static final String TAG = "ListingAdapter";
    ArrayList<RestaurantInfo> mObjects = new ArrayList<RestaurantInfo>();

    Context mContext;


    //constructor
    public ListingAdapter(Context context,
                          ArrayList<RestaurantInfo> objects) {

        mObjects = objects;
        mContext = context;
    }


    /* setuo recycler view adapter*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder parseViewHolder, int i) {

        parseViewHolder.bindObject(mObjects.get(i));
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder.mExpandedLayout.getVisibility() == View.VISIBLE) {
            holder.quickCollapse(holder.mExpandedLayout);
        }
    }


    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable;
        isAvailable = networkInfo != null && networkInfo.isConnected();
        return isAvailable;
    }

 

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Declare views to be filled
       //compressed views
        @InjectView(R.id.compressed)
        RelativeLayout mCompressedLayout;
        @InjectView(R.id.nameCompressed)
        TextView mNameCompressed;
        @InjectView(R.id.addressCompressed)
        TextView mAddressCompressed;
        @InjectView(R.id.ratingCompressed)
        ImageView  mRatingCompressed;
        @InjectView(R.id.imageCompressed)
        CircleImageView mImageCompressed;

       //expanded views
        @InjectView(R.id.expanded)
        LinearLayout mExpandedLayout;
        @InjectView(R.id.nameExpanded)
        TextView mNameExpanded;
        @InjectView(R.id.addressExpanded)
        TextView mAddressExpanded;
        @InjectView(R.id.imageExpanded)
        ImageView mImageExpanded;
        @InjectView(R.id.shadow)
        ImageView mShadow;
        @InjectView(R.id.detailsButton)
        ButtonFlat mDetailsButton;
        @InjectView(R.id.directionsButton)
        ButtonFlat mDirectionsButton;




        public ViewHolder(final View itemView) {
            super(itemView);

            // Define views to be filled
            ButterKnife.inject(this, itemView);
            mCompressedLayout.setOnClickListener(this);
        }


        //action when view is clicked
        // either expand or collapse
        @Override
        public void onClick(final View v) {
            if (mExpandedLayout.getVisibility() == View.GONE) {
                expand(mExpandedLayout);

            } else if (mExpandedLayout.getVisibility() == View.VISIBLE) {
                collapse(mExpandedLayout);
            }
        }


        //bind the view
        public void bindObject(final RestaurantInfo restaurantInfo) {

            final String name = restaurantInfo.getRestaurantName();
            final String address = restaurantInfo.getAddress();
            final String imageUrl = restaurantInfo.getImageURL();


            // set up default compressed view
            Picasso.with(itemView.getContext()).load(imageUrl).into(mImageCompressed);
            mNameCompressed.setText(name);
            mAddressCompressed.setText(address);
            Picasso.with(itemView.getContext()).load(restaurantInfo.getRatingImgURL()).into(mRatingCompressed);


            //set up expanded view which will visible when clicked
            Picasso.with(itemView.getContext()).load(restaurantInfo.getImageURL()).into(mImageExpanded);
            Picasso.with(itemView.getContext()).load(R.drawable.shadow).into(mShadow);
            mNameExpanded.setText(name);
            mAddressExpanded.setText(address);


            // Define actions for Details button
            mDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("restaurant", restaurantInfo);
                    Intent detailsIntent = new Intent(mContext,DetailedView.class);
                    detailsIntent.putExtras(bundle);
                    detailsIntent.putExtra(Intent.EXTRA_TEXT, name);
                    mContext.startActivity(detailsIntent);

                }
            });

            // Define actions for Directions button
            mDirectionsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Create label to pass to Google Maps, will be displayed as location name
                    String label = " (" + name + ")";

                    // Parse parameters as proper URI format, create Intent
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address + label);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                    // Define app to receive intent, make sure it exists
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(mContext.getPackageManager()) != null
                                            && isNetworkAvailable()) {
                        // If app exists, send intent
                        mContext.startActivity(mapIntent);
                    } else {
                        // If not, show error toast, do not send
                        Toast.makeText(mContext, "Unable to load maps application",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });




        }

        /*
        expand for click
         */

        public void expand(final View v) {
            v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final int targetHeight = v.getMeasuredHeight();

            v.getLayoutParams().height = 0;
            v.setVisibility(View.VISIBLE);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? LinearLayout.LayoutParams.WRAP_CONTENT
                            : (int) (targetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
            Log.d(TAG, "expanding");
            v.startAnimation(a);
        }


        /*
        Collapse
        for click
         */
        public void collapse(final View v) {
            final int initialHeight = v.getMeasuredHeight();

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime == 1) {
                        v.setVisibility(View.GONE);
                    } else {
                        v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                        v.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
            Log.d(TAG, "collapsing");
            v.startAnimation(a);
        }



        /*
        Default collapse for the expanded view
         */

        public void quickCollapse(final View v) {
            final int initialHeight = v.getMeasuredHeight();

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime == 1) {
                        v.setVisibility(View.GONE);
                    } else {
                        v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                        v.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setDuration(10);
            v.startAnimation(a);
        }

    }
}

