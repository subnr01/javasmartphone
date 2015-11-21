package com.nihar.java_assignment.foureverhungry.remote.yelp;

import android.media.session.MediaSession;
import android.util.Log;

import com.nihar.java_assignment.foureverhungry.local.model.RestaurantInfo;
import com.nihar.java_assignment.foureverhungry.local.model.Review;
import com.nihar.java_assignment.foureverhungry.local.model.SearchInfo;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.json.JSONException;
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

import java.util.ArrayList;

/**
 * Created by Sudhir Ravi on 11/15/15.
 */
public class SearchUtil {
    private final String consumerKey = "J2ZoDSYwHydvS9lMxF7PiQ";
    private final String consumerSecret = "l640aej7VUFj-u8NCjQniizWRSw";
    private final String token ="afsNscleumSppliqxJIHrNok8SglDjsp";
    private final String tokenSecret = "ZucLA7CGZvIbqBP1ehOa4q-R3Ys";

    private SearchInfo searchInfo;
    OAuthService service;
    Token accessToken;

    public SearchUtil(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
        this.service = new ServiceBuilder().provider(YelpApi.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    public ArrayList<RestaurantInfo> search() {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        Log.d("INPUT TO YELP CUISINE", searchInfo.getCuisine());
        Log.d("INPUT TO YELP LOCATION", searchInfo.getLocation());
        Log.d("INPUT TO YELP RADIUS", String.valueOf(searchInfo.getDistance()));

        request.addQuerystringParameter("term", searchInfo.getCuisine());
        request.addQuerystringParameter("location", searchInfo.getLocation());
        request.addQuerystringParameter("radius_filter", String.valueOf((int) searchInfo.getDistance()));
        Log.d("YELP REQUEST QUERY", request.toString());
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();

        ArrayList<RestaurantInfo> result = new ArrayList<RestaurantInfo>();
 //       try {
            //JSONObject jsonObject = new JSONObject(response.getBody());
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
            Log.d("NUM RES RESPONSES", String.valueOf(restaurants.size()));
            for(int idx = 0; idx < restaurants.size(); idx++) {
                RestaurantInfo restaurant = new RestaurantInfo();
                Log.d("REST NO" + idx, restaurants.get(idx).toString());

                JSONObject restObj = (JSONObject)restaurants.get(idx);
                Log.d("LOCATION", restObj.get("location").toString());
                Log.d("ADDRESS", ((JSONObject)restObj.get("location")).get("address").toString());

                Log.d("DISPLAY PHONE", restObj.get("display_phone").toString());
                Log.d("URL", restObj.get("image_url").toString());


                restaurant.setAddress(((JSONObject) restObj.get("location")).get("address").toString());
                restaurant.setPhone(restObj.get("display_phone").toString());
                restaurant.setImage(restObj.get("image_url").toString());
                // Read reviews should be from the listings page if the user clicks on the particular restaurant. This may affect design of retrieval of old search items if the reviews
                // have never been populated by the user.
                OAuthRequest reviewRequest = new OAuthRequest(Verb.GET, restObj.get("url").toString());
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

                Log.d("NUM RES REVIEWS", Integer.toString(reviews.size()));

                for(int jdx = 0; jdx < reviews.size(); jdx++) {
                    Log.d("REVIEW NO" + jdx, reviews.get(jdx).toString());
                    JSONObject reviewObject = (JSONObject)reviews.get(jdx);
                    Log.d("REVIEW EXCERPT", reviewObject.get("excerpt").toString());
                    Log.d("REVIEW USER URL", ((JSONObject)reviewObject.get("user")).get("image_url").toString());

                    Review review = new Review(reviewObject.get("excerpt").toString(),
                            ((JSONObject)reviewObject.get("user")).get("image_url").toString());
                    restaurant.addReview(review);

                }

                result.add(restaurant);
            }

            return result;
        } //catch (JSONException e) {
          //  Log.d("YELP search failed:", e.getMessage());
       // }

        //return null;
    //}
}