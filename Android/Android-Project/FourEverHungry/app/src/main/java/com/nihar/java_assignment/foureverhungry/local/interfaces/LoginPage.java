package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.UserInfo;

public class LoginPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void goToWelcomePage(View sender) {
        String email, password;
        EditText et = (EditText)findViewById(R.id.editText2);
        email = et.getText().toString();
        et = (EditText)findViewById(R.id.editText2);
        password = et.getText().toString();

        UserInfo login = new UserInfo(email, password);
        if(login.authenticate()) {
            Intent intent = new Intent(LoginPage.this, WelcomePage.class);
            startActivity(intent);
            finish();
        } else {
            Toast toast = Toast.makeText(this, "e-mail/password is incorrect. Please check", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void goToRegistrationPage(View sender) {
        Intent intent = new Intent(LoginPage.this, RegistrationPage.class);
        startActivity(intent);
        finish();
    }
}
