package edu.cmu.foreverhungry.ui.login;
/**
 * Created by subs on 11/26/15.
 */

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.Random;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.ui.welcome.WelcomePage;

public class LoginScreenActivity extends FragmentActivity implements
            LoginFragmentListener,LoginSuccessListener,ForgotPasswordSuccessListener {


    public static final String TAG = "LoginScreen";
    private ImageView loginWallPaper;
    private final int fragmentContainer = R.id.loginContainer;
    private boolean destroyed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_screen);
        loginWallPaper = (ImageView) findViewById(R.id.loginImage);

        //randomImage();

        if (savedInstanceState == null) {
            loadLoginFragment();
        }
    }

    /*
    * Load the login fragment
    */
    public void loadLoginFragment(){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(fragmentContainer, loginFragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
    }


    public void onLoginSuccess(String username) {
        Intent intent = new Intent(this, WelcomePage.class);
        //intent.putExtra("username", username);
        startActivity(intent);
    }


    public void onClickSuccess() {

        getSupportFragmentManager().popBackStackImmediate();
    }





    @Override
    public void onForgotPasswordClicked() {
        // Show the login help form for resetting the user's password.
        // Keep the transaction on the back stack so that if the user clicks
        // the back button, they are brought back to the login form.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       // transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,  R.anim.enter_from_right, R.anim.exit_to_left);

        ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
        transaction.replace(fragmentContainer, forgotPasswordFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    @Override
    public void OnRegisterClicked(String username, String password) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        transaction.replace(fragmentContainer,registerFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        Picasso.with(this).load(toLoad).into(loginWallPaper);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}


