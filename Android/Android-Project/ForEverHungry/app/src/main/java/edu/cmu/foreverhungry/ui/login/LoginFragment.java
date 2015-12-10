package edu.cmu.foreverhungry.ui.login;

/**
 * Created by subs on 11/25/15.
 */
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.cmu.foreverhungry.R;



public class LoginFragment extends LoginFragmentBase {

    private static final String TAG = "LoginFragment";

    private View loginPage;
    private EditText usernameField;
    private EditText passwordField;
    private Button ForgotPasswordButton;
    private Button LoginButton;
    private Button RegisterButton;
    private LoginFragmentListener loginFragmentListener;
    private LoginSuccessListener loginSuccessListener;
    private static boolean RUNWITHOUTAUTH = false;
    protected String username;
    protected String password;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_fragment,container, false);

        ImageView appLogo = (ImageView) v.findViewById(R.id.app_logo);
        loginPage = v.findViewById(R.id.login);

        usernameField = (EditText) v.findViewById(R.id.login_username_input);
        passwordField = (EditText) v.findViewById(R.id.login_password_input);
        ForgotPasswordButton = (Button) v.findViewById(R.id.forgot_password_button);
        LoginButton = (Button) v.findViewById(R.id.login_button);
        RegisterButton = (Button)v.findViewById(R.id.register_button);


        if (appLogo != null) {
            appLogo.setImageResource(R.drawable.logo);
        }

        setup();

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof LoginFragmentListener) {
            loginFragmentListener = (LoginFragmentListener) activity;
        } else {
            throw new IllegalArgumentException();
        }

        if (activity instanceof LoginSuccessListener) {
            loginSuccessListener = (LoginSuccessListener) activity;
        } else {
            throw new IllegalArgumentException();

        }

    }

    private void ProcessLogin() {

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if ( RUNWITHOUTAUTH) {
            loginSuccess(username);
            return;
        }


        if (username.length() == 0) {
            showToast(R.string.no_username_toast);
            return;
        } else if (password.length() == 0) {
            showToast(R.string.no_password_toast);
            return;
        }

        this.username = username;
        this.password = password;
        if (username.equals("admin")) {
            return;
        }
        (new Authenticate()).execute();
    }




    private void setup()
    {
        loginPage.setVisibility(View.VISIBLE);
        LoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessLogin();

            }
        });


        RegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                loginFragmentListener.OnRegisterClicked(username, password);
            }
        });

        ForgotPasswordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragmentListener.onForgotPasswordClicked();
            }
        });
    }


    private void loginSuccess(String username)
    {
        loginSuccessListener.onLoginSuccess(username);
    }

    private class Authenticate extends AsyncTask {
        private boolean success = false;

        @Override
        protected Object doInBackground(Object[] params) {
            String result = null;
            try {
                String ServerIP = getResources().getString(R.string.ServerIP);
                Integer port = Integer.valueOf(getResources().getString(R.string.ServerPort));
                Log.v("subbu3", ServerIP);
                Log.v("subbu3", port.toString());
                Socket socket = new Socket(ServerIP, port);

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject("passwordcheck");
                out.writeObject(username);
                out.writeObject(password);
                result = (String)in.readObject();
            } catch (Exception e) {
                Log.d("ERROR", "Login failed" + e.getMessage());
                return false;
            }

            if(result.equals("SUCCESS")) {
                success = true;
                return username;
            }

            return null;
        }

        protected void onPostExecute(final Object loginResults) {
            if(success) {
                loginSuccess(username);
                return;
            }

            showToast("Invalid username/password. Try again!");
        }
    }
}
