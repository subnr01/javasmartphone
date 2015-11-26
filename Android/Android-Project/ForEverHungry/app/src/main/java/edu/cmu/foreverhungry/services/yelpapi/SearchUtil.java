package edu.cmu.foreverhungry.services.yelpapi;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.media.session.MediaSession;
import android.util.Log;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import edu.cmu.foreverhungry.model.*;
import edu.cmu.foreverhungry.R;


/**
 * Created by Sudhir Ravi on 11/15/15.
 */
public class SearchUtil {
    private final String consumerKey = "J2ZoDSYwHydvS9lMxF7PiQ";
    private final String consumerSecret = "l640aej7VUFj-u8NCjQniizWRSw";
    private final String token ="afsNscleumSppliqxJIHrNok8SglDjsp";
    private final String tokenSecret = "ZucLA7CGZvIbqBP1ehOa4q-R3Ys";
    private Context context;

    private SearchInfo searchInfo;
    OAuthService service;
    Token accessToken;

    public SearchUtil(SearchInfo searchInfo, Context context) {
        this.searchInfo = searchInfo;

        this.service = new ServiceBuilder().provider(YelpApi.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
        this.context = context;
    }

    public ArrayList<RestaurantInfo> search() {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        Log.d("INPUT TO YELP CUISINE", searchInfo.getCuisine());
        String locationQuery = searchInfo.getLocation();
        double currLatitude = searchInfo.getLatitude();
        double currLongitude = searchInfo.getLongitude();

        if (locationQuery != null) {
            Log.d("INPUT TO YELP LOCATION", locationQuery);
        }
        Log.d("INPUT TO YELP RADIUS", String.valueOf(searchInfo.getDistance()));
        //if (currLatitude != 0.0 && currLongitude != 0.0) {
        Log.d("INPUT TO YELP LATITUDE", String.valueOf(currLatitude));
        Log.d("INPUT TO YELP LATITUDE", String.valueOf(currLongitude));
        //}
        request.addQuerystringParameter("term", "food" + " " + searchInfo.getCuisine());
        if (locationQuery != null) {
            request.addQuerystringParameter("location", locationQuery);
        } else {
            // cll=37.77493,-122.419415
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(currLatitude, currLongitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses.size() > 0)
                Log.d("SEARCH UTIL GOT CITY", addresses.get(0).getLocality());
            request.addQuerystringParameter("location",addresses.get(0).getLocality() );
            request.addQuerystringParameter("cll", currLatitude + "," + currLongitude);
        }
        request.addQuerystringParameter("radius_filter", String.valueOf((int) searchInfo.getDistance()));

        /* Should be less than 20 according to API documentation */
        Log.d("LIMITING RESULTS TO ", context.getString(R.string.maxListings));
        request.addQuerystringParameter("limit", context.getString(R.string.maxListings));

        Log.d("YELP REQUEST QUERY", request.toString());
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();

        ArrayList<RestaurantInfo> result = new ArrayList<RestaurantInfo>();

        String searchResponseJSON = response.getBody();
        System.out.println("RestResponse " + searchResponseJSON);

        JSONParser parser = new JSONParser();
        JSONObject temp = null;
        try {
            temp = (JSONObject) parser.parse(searchResponseJSON);
        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(searchResponseJSON);
            return null;
        }
        JSONArray restaurants = (JSONArray) temp.get("businesses");
        if (restaurants == null) {
            return null;
        }
        Log.d("NUM RES RESPONSES", String.valueOf(restaurants.size()));
        for(int idx = 0; idx < restaurants.size(); idx++) {
            RestaurantInfo restaurant = new RestaurantInfo();
            Log.d("REST NO" + idx, restaurants.get(idx).toString());

            JSONObject restObj = (JSONObject) restaurants.get(idx);

            if (restObj.get("id") != null) {
                Log.d("ID", restObj.get("id").toString());
            }
            if (restObj.get("review_count") != null) {
                Log.d("NUM REVIEWS", (restObj.get("review_count").toString()));
            }


            JSONObject location = (JSONObject) restObj.get("location");
            if (location == null) {
                Log.d("LOCATION", "NULL");
                continue;
            }
            JSONObject coordinate = (JSONObject) location.get("coordinate");
            if (coordinate == null) {
                Log.d("COORDINATE", "NULL");
                continue;
            }
            double latitude = (double) coordinate.get("latitude");

            double longitude = (double) coordinate.get("longitude");

            //Log.d("Latitude", (.get("latitude").toString());
            //Log.d("Longitude", ((JSONObject) restObj.get("center")).get("longitude").toString());

            if (restObj.get("location") != null && ((JSONObject) restObj.get("location")).get("address") != null) {
                Log.d("LOCATION", restObj.get("location").toString());
                Log.d("ADDRESS", ((JSONObject) restObj.get("location")).get("address").toString());
                restaurant.setAddress(((JSONObject) restObj.get("location")).get("address").toString());
            }
            if (restObj.get("display_phone") != null) {
                Log.d("DISPLAY PHONE", restObj.get("display_phone").toString());
                restaurant.setPhone(restObj.get("display_phone").toString());
            }
            if (restObj.get("image_url") != null) {
                Log.d("URL", restObj.get("image_url").toString());
                restaurant.setImage(restObj.get("image_url").toString());
            }
            if (restObj.get("name") != null) {
                restaurant.setRestaurantName(restObj.get("name").toString());
            }
            if (restObj.get("rating_img_url_large") != null) {
                restaurant.setRatingImg(restObj.get("rating_img_url_large").toString());
            }
            restaurant.setLocation(latitude,
                    longitude);


            // Read reviews should be from the listings page if the user clicks on the particular restaurant. This may affect design of retrieval of old search items if the reviews
            // have never been populated by the user.

            if (restObj.get("id") == null) {
                continue;
            }

            OAuthRequest reviewRequest = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/business/" + restObj.get("id").toString() );
            this.service.signRequest(this.accessToken, reviewRequest);
            Response reviewResponse = reviewRequest.send();

            String reviewResponseJSON1 = reviewResponse.getBody();
            System.out.println("ReviewResponse " + reviewResponseJSON1);

            JSONParser reviewparser = new JSONParser();
            JSONObject tempreview = null;
            try {
                tempreview = (JSONObject) reviewparser.parse(reviewResponseJSON1);
            } catch (ParseException pe) {
                System.out.println("Error: could not parse JSON response for reviews:" + pe.getMessage());
                System.out.println(searchResponseJSON);
                return null;
            }

            JSONArray reviews = (JSONArray) tempreview.get("reviews");
            //JSONArray reviews = (JSONArray) restObj.get("reviews");

            if (reviews == null) {
                continue;
            }
            Log.d("NUM RES REVIEWS", Integer.toString(reviews.size()));

            for(int jdx = 0; jdx < reviews.size(); jdx++) {

                Log.d("REVIEW NO" + jdx, reviews.get(jdx).toString());
                JSONObject reviewObject = (JSONObject)reviews.get(jdx);
                Log.d("REVIEW EXCERPT", reviewObject.get("excerpt").toString());
                Log.d("REVIEW USER URL", ((JSONObject)reviewObject.get("user")).get("image_url").toString());
                Log.d("REVIEW USER NAME", ((JSONObject)reviewObject.get("user")).get("name").toString());

                Review review = new Review(
                        ((JSONObject)reviewObject.get("user")).get("name").toString(),
                        reviewObject.get("excerpt").toString(),
                        ((JSONObject)reviewObject.get("user")).get("image_url").toString());
                restaurant.addReview(review);

            }

            result.add(restaurant);
        }

        return result;
    }

}
