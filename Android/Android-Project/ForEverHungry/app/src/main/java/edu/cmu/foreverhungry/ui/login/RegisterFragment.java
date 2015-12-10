package edu.cmu.foreverhungry.ui.login;
/**
 * Created by subs on 11/26/15.
 */

import android.app.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.RegistrationInfo;
import edu.cmu.foreverhungry.model.RestaurantInfo;
import edu.cmu.foreverhungry.model.UserInfo;


public class RegisterFragment extends LoginFragmentBase implements OnClickListener {
    private EditText usernameField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private EditText emailField;
    protected String username;
    protected String password;
    protected String email;

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
        emailField = (EditText) v.findViewById(R.id.signup_email_input);
        confirmPasswordField = (EditText) v
                .findViewById(R.id.signup_confirm_password_input);
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
        if(v.getId() != R.id.create_account) {
            assert (false) : "Button click callback for unexpected button " + v.getId();
        }
        register(v);
        return;
    }

    private void registerSuccess() {
        Toast.makeText(getActivity(), "Account created", Toast.LENGTH_SHORT).show();
        loginSuccessListener.onLoginSuccess(username);
    }

    private boolean validateRegister(String username, String password,
                                            String passwordAgain, String email) {
        if (username.length() == 0) {
            showToast(R.string.no_username_toast);
            return false;
        } else if (password.length() == 0) {
            showToast(R.string.no_password_toast);
            return false;
        } else if (password.length() < minPasswordLength) {
            showToast(getResources().getQuantityString(
                    R.plurals.password_too_short_toast,
                    minPasswordLength, minPasswordLength));
            return false;
        } else if (passwordAgain.length() == 0) {
            showToast(R.string.reenter_password_toast);
            return false;
        } else if (!password.equals(passwordAgain)) {
            showToast(R.string.mismatch_confirm_password_toast);
            confirmPasswordField.selectAll();
            confirmPasswordField.requestFocus();
            return false;
        }else if (email != null && email.length() == 0) {
            showToast(R.string.no_email_toast);
            return false;
        }

        return true;
    }

    // Returns success status of registration
    private void register(View v) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String passwordAgain = confirmPasswordField.getText().toString();
        String email = emailField.getText().toString();

        if(!validateRegister(username, password, passwordAgain, email)) {
            return;
        }

        this.username = username;
        this.password = password;
        this.email = email;
        new Registration().execute();
    }

    private class Registration extends AsyncTask {
        private boolean success = false;
        private boolean complete = false;
        private String user;

        @Override
        protected Object doInBackground(Object[] params) {
            String result = null;
            try {
                Log.d("REGISTER FRAGMENT", "Before socket");
                String ServerIP = getResources().getString(R.string.ServerIP);
                Integer port = Integer.valueOf(getResources().getString(R.string.ServerPort));
                Log.d("REGISTER FRAGMENT IP", ServerIP);
                Log.d("REGISTER FRAGMENT PORT", port.toString());

                Socket socket = new Socket(ServerIP, port);
                Log.d("REGISTER FRAGMENT", "Before IN");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Log.d("REGISTER FRAGMENT", "Before OUT");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                Log.d("REGISTER FRAGMENT", "After OUT");
                out.writeObject("newuser");
                out.writeObject(username);
                out.writeObject(password);
                out.writeObject(email);
                result = (String)in.readObject();
            } catch (IOException e) {
                Log.d("ERROR", e.getMessage());
                return null;
            } catch (ClassNotFoundException e) {
                Log.d("ERROR", e.getMessage());
                e.printStackTrace();
                return null;
            }

            if(result.equals("SUCCESS")) {
                success = true;
                UserInfo.getInstance().setUsername(username);
                UserInfo.getInstance().setEmail(email);
                UserInfo.getInstance().setPassword(password);
            } else {
                complete = true;
            }

            return new Boolean(false);
        }

        protected void onPostExecute(final Object registrationResults) {
            if (success == true) {
                registerSuccess();
                return;
            } else if (complete == true){
                showToast("Username already exists. Choose a different one");
        }
        }
    }
}