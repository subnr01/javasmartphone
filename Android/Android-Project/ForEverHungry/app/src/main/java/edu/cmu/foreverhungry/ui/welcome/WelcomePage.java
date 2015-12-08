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
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.Menu;
import com.squareup.picasso.Picasso;

import java.util.Random;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.ui.search.*;

public class WelcomePage extends Activity {

    private RelativeLayout WallPaper;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra("username");
        if (username == null) {
            Log.d("WELCOME PAGE ERROR:", "USERNAME IS NULL");
        }

        setContentView(R.layout.activity_welcome_page);
        WallPaper = (RelativeLayout) findViewById(R.id.welcome_page);
        randomImage();
    }

    public void goToSearchPage(View sender) {
        Log.d("WELCOME PAGE", "GOING TO SEARCHOPTIONS PAGE");
        Intent intent = new Intent(WelcomePage.this, SearchOptionsPage.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void goToSavedSearches(View sender) {
        Intent intent = new Intent(WelcomePage.this, SavedSearchesPage.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }



    private void randomImage() {
        Random rand = new Random();
        int random = rand.nextInt(10);
        int toLoad = 0;
        switch (random) {
            case 0:
                toLoad = R.drawable.chinese;
                break;
            case 1:
                toLoad = R.drawable.thai;
                break;
            case 2:
                toLoad = R.drawable.italian;
                break;
            case 3:
                toLoad = R.drawable.mexican;
                break;
            case 4:
                toLoad = R.drawable.mediterranean;
                break;
            case 5:
                toLoad = R.drawable.indian;
                break;
            case 6:
                toLoad = R.drawable.japanese;
                break;
            case 7:
                toLoad = R.drawable.korean;
                break;
            case 8:
                toLoad = R.drawable.burger;
                break;
            case 9:
                toLoad = R.drawable.arabian;
                break;



        }
        WallPaper.setBackgroundResource(toLoad);

    }
}

