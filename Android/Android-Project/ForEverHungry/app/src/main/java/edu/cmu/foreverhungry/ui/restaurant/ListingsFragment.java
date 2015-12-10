package edu.cmu.foreverhungry.ui.restaurant;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.SearchInfo;
import edu.cmu.foreverhungry.services.yelpapi.SearchUtil;

/**
 * Created by subs on 12/3/15.
 */
public class ListingsFragment extends Fragment {

    /*
    Listing fragment to display restuarant info
     */


    private static final String TAG = "ListingsFragment";

    // for fitting the restuarant info in the screen
    RecyclerView mRecyclerView;

    //adapter for the recuvler view
    RecyclerView.Adapter mAdapter;

    //refresh on swipe
    SwipeRefreshLayout mSwipeRefreshLayout;

    //progress bar until results retrieved
    ProgressBar mProgressBar;

    //results
    ArrayList<RestaurantInfo> searchResults;

    //if no results then display
    TextView mEmptyText;

    private String cuisineInput ;
    private String locationInput;
    private double latitude;
    private double longitude;
    private double distance;







    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.listings_fragment, container, false);

        //get the user information
        cuisineInput = getArguments().getString("cuisine");
        locationInput = getArguments().getString("location");
        latitude = getArguments().getDouble("latitude");
        longitude = getArguments().getDouble("longitude");
        distance = getArguments().getDouble("distance");


        Log.v(TAG,String.valueOf(latitude));
        Log.v(TAG, String.valueOf(longitude));
        Log.v(TAG, String.valueOf(distance));
        Log.v(TAG,cuisineInput);


        searchResults = new ArrayList<>();
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress);
        mEmptyText = (TextView) v.findViewById(R.id.emptyText);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresher);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setEnabled(false);
                getListings();
            }
        });

        //set up recycler view
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setPadding(0, 0, 0, 400);
        mRecyclerView.setClipToPadding(false);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //get the restuarant info
        getListings();

        //setup recycler view adapter
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        return v;
    }



    private void getListings() {
        if (isNetworkAvailable()) {
            Context context = getActivity().getApplicationContext();

            //start the async task
            new ShowListings(context).execute();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Network unavailable", Toast.LENGTH_LONG).show();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable;
        isAvailable = networkInfo != null && networkInfo.isConnected();
        return isAvailable;
    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private class ShowListings extends AsyncTask<Integer, Void, List<RestaurantInfo>> {
        private Context mContext;

        public ShowListings(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mEmptyText.setVisibility(View.GONE);
            if (mProgressBar.isEnabled()) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected List<RestaurantInfo> doInBackground(Integer... params) {

            Log.v("subbu1","background");
            Log.v("subbu1",locationInput);
            Log.v("subbu1",cuisineInput);
            Log.v("subbu1",String.valueOf(distance));
            Log.v("subbu1",String.valueOf(latitude));
            Log.v("subbu1",String.valueOf(longitude));


            //call yelp
            SearchInfo searchInfo = new SearchInfo(locationInput, cuisineInput, distance, latitude, longitude);
            SearchUtil searchUtil = new SearchUtil(searchInfo, mContext);
            searchResults = searchUtil.search();
            if (searchResults == null) {
                return null;
            }
            Log.v(TAG, "got " + searchResults.size() + " objects");
            //setup the adapter
            mAdapter = new ListingAdapter(getActivity(), searchResults);
            // update this info in the fragment in the case that the search needs to be saved
            SearchInfo saveInfo = new SearchInfo(locationInput, cuisineInput, distance, latitude, longitude);
            Activity listing = getActivity();
            if (listing != null) {
                ((ListingsPage)listing).updateSearchInfo(saveInfo);
                ((ListingsPage)listing).returnSearchResults(searchResults);

            } else {
                Log.d("LISTING FRAGMENT", "NULL ACTIVITY OBJECT");
            }


            return searchResults;
       }

        @Override
        protected void onPostExecute(final List<RestaurantInfo> searchResults) {
            Log.v("subbu1", "ListingsFragment ShowListings postExecute");
            super.onPostExecute(searchResults);
            if (mAdapter == null) {
                Log.v("subbu1", "adapter is null");
            }

            if (searchResults == null) {
                mEmptyText.setVisibility(View.VISIBLE);
            }
            else if (searchResults.size() == 0) {
                mEmptyText.setVisibility(View.VISIBLE);
            }

            //update UI with the adapter info
            mRecyclerView.setAdapter(mAdapter);
            mSwipeRefreshLayout.setRefreshing(false);
            mProgressBar.setVisibility(View.GONE);
            mProgressBar.setEnabled(true);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }


}
