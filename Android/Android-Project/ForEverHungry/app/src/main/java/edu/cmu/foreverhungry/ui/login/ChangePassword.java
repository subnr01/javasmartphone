package edu.cmu.foreverhungry.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.UserInfo;

public class ChangePassword extends Activity implements View.OnClickListener {

    private EditText passwordField;
    private EditText confirmPasswordField;
    private Button submitButton;

    private int minPasswordLength;

    private static final String TAG = "ChangePassword";
    private static final int DEFAULT_MIN_PASSWORD_LENGTH = 6;

    private boolean changePassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        passwordField = (EditText) findViewById(R.id.signup_password_input);
        confirmPasswordField = (EditText) findViewById(R.id.signup_confirm_password_input);
        submitButton = (Button) findViewById(R.id.create_account);

        submitButton.setOnClickListener(this);


    }


    public void onClick(View v) {

        String password = passwordField.getText().toString();
        String passwordAgain = confirmPasswordField.getText().toString();

        if (password.length() == 0) {
            showToast(getString(R.string.no_password_toast));
            return;
        } else if (password.length() < minPasswordLength) {
            showToast(getResources().getQuantityString(
                    R.plurals.password_too_short_toast,
                    minPasswordLength, minPasswordLength));
            return;
        } else if (passwordAgain.length() == 0) {
            showToast(getString(R.string.reenter_password_toast));
            return ;
        } else if (!password.equals(passwordAgain)) {
            showToast(getString(R.string.mismatch_confirm_password_toast));
            confirmPasswordField.selectAll();
            confirmPasswordField.requestFocus();
            return;
        }

        updateRemote();



        changePassword = true;
        if (changePassword) {
            UserInfo.clearUserInfo();
            Intent intent = new Intent(this, LoginScreenActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }

    }

    protected void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    private void updateRemote()
    {

    }

}

