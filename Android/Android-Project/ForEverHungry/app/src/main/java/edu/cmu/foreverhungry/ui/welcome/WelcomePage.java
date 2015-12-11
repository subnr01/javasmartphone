package edu.cmu.foreverhungry.ui.welcome;
/**
 * Created by subs on 11/25/15.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.Menu;
import com.squareup.picasso.Picasso;

import java.util.Random;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.UserInfo;
import edu.cmu.foreverhungry.ui.login.ChangePassword;
import edu.cmu.foreverhungry.ui.login.LoginScreenActivity;
import edu.cmu.foreverhungry.ui.search.*;

public class WelcomePage extends Activity {

    private LinearLayout WallPaper;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = UserInfo.getInstance().getUsername();
        if (username == null) {
            Log.d("WELCOME PAGE ERROR:", "USERNAME IS NULL");
        }

        setContentView(R.layout.activity_welcome_page);
        WallPaper = (LinearLayout) findViewById(R.id.welcome_page);
        randomImage();
    }

    public void goToSearchPage(View sender) {
        Log.d("WELCOME PAGE", "GOING TO SEARCHOPTIONS PAGE");
        Intent intent = new Intent(WelcomePage.this, SearchOptionsPage.class);
        startActivity(intent);
    }
    public void goToSavedSearches(View sender) {
        Intent intent = new Intent(WelcomePage.this, SavedSearchesPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void randomImage() {
        Random rand = new Random();
        int random = rand.nextInt(10);
        int toLoad = 0;
        switch (random) {
            case 0:
                toLoad = R.drawable.img1;
                break;
            case 1:
                toLoad = R.drawable.img2;
                break;
            case 2:
                toLoad = R.drawable.img3;
                break;
            case 3:
                toLoad = R.drawable.img4;
                break;
            case 4:
                toLoad = R.drawable.img5;
                break;
            case 5:
                toLoad = R.drawable.img6;
                break;
            case 6:
                toLoad = R.drawable.img7;
                break;
            case 7:
                toLoad = R.drawable.img8;
                break;
            case 8:
                toLoad = R.drawable.img9;
                break;
            case 9:
                toLoad = R.drawable.img10;
                break;

        }
        WallPaper.setBackgroundResource(toLoad);

    }
}

