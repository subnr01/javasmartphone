package edu.cmu.foreverhungry.ui.login;
/**
 * Created by subs on 11/26/15.
 */

import android.app.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import edu.cmu.foreverhungry.R;


public class RegisterFragment extends LoginFragmentBase implements OnClickListener {


    private EditText usernameField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private EditText emailField;

    private Button createAccountButton;
    private LoginSuccessListener loginSuccessListener;

    private int minPasswordLength;

    private static final String TAG = "RegisterFragment";
    private static final int DEFAULT_MIN_PASSWORD_LENGTH = 6;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {


        minPasswordLength = DEFAULT_MIN_PASSWORD_LENGTH;


        View v = inflater.inflate(R.layout.registerpage_fragment,
                parent, false);

        usernameField = (EditText) v.findViewById(R.id.signup_username_input);
        passwordField = (EditText) v.findViewById(R.id.signup_password_input);
        confirmPasswordField = (EditText) v
                .findViewById(R.id.signup_confirm_password_input);
        emailField = (EditText) v.findViewById(R.id.signup_email_input);
        createAccountButton = (Button) v.findViewById(R.id.create_account);






        createAccountButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LoginSuccessListener) {
            loginSuccessListener = (LoginSuccessListener) activity;
        } else {
            throw new IllegalArgumentException();

        }
    }

    @Override
    public void onClick(View v) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String passwordAgain = confirmPasswordField.getText().toString();

        String email = null;
        email = emailField.getText().toString();

        if (username.length() == 0) {
                showToast(R.string.no_username_toast);

        } else if (password.length() == 0) {
            showToast(R.string.no_password_toast);
        } else if (password.length() < minPasswordLength) {
            showToast(getResources().getQuantityString(
                    R.plurals.password_too_short_toast,
                    minPasswordLength, minPasswordLength));
        } else if (passwordAgain.length() == 0) {
            showToast(R.string.reenter_password_toast);
        } else if (!password.equals(passwordAgain)) {
            showToast(R.string.mismatch_confirm_password_toast);
            confirmPasswordField.selectAll();
            confirmPasswordField.requestFocus();
        } else if (email != null && email.length() == 0) {
            showToast(R.string.no_email_toast);
        } else {
            registerSuccess();

        }
    }


    private void registerSuccess() {
        Toast.makeText(getActivity(), "Account created", Toast.LENGTH_SHORT).show();
        loginSuccessListener.onLoginSuccess();
    }




}


