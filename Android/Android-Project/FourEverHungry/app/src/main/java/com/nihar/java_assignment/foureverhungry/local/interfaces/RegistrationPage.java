package com.nihar.java_assignment.foureverhungry.local.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nihar.java_assignment.foureverhungry.R;
import com.nihar.java_assignment.foureverhungry.local.model.RegistrationInfo;

public class RegistrationPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
    }

    public void goToWelcomePage(View sender) {
        String name;
        String email;
        String password;
        String confirmPassword;

        EditText et = (EditText)findViewById(R.id.editText4);
        name = et.getText().toString();
        et = (EditText)findViewById(R.id.editText5);
        email = et.getText().toString();
        et = (EditText)findViewById(R.id.editText6);
        password = et.getText().toString();
        et = (EditText)findViewById(R.id.editText7);
        confirmPassword = et.getText().toString();

        RegistrationInfo reg = new RegistrationInfo(name, email, password, confirmPassword);
        if(reg.validate()) {
            reg.register();
        }

        Intent intent = new Intent(RegistrationPage.this, WelcomePage.class);
        startActivity(intent);
        finish();
    }
}
