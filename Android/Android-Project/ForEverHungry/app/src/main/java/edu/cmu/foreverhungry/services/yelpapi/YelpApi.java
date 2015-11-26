package edu.cmu.foreverhungry.services.yelpapi;

import android.media.session.MediaSession;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Created by Sudhir Ravi on 11/15/15.
 */
/*
public class YelpApi {

}
*/
public class YelpApi extends DefaultApi10a {
    @Override
    public String getAccessTokenEndpoint() {
        return null;
    }

    @Override
    public String getAuthorizationUrl(Token arg0) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }

}

