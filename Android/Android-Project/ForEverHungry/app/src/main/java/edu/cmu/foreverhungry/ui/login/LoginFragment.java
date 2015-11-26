package edu.cmu.foreverhungry.ui.login;

/**
 * Created by subs on 11/25/15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import edu.cmu.foreverhungry.R;


/**
 * Fragment for the user login screen.
 */
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
    private static boolean RUNWITHOUTAUTH = true;


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
            loginSuccess();
            return;
        }
        if (username.length() == 0) {
            showToast(R.string.no_username_toast);

        } else if (password.length() == 0) {
            showToast(R.string.no_password_toast);
        } else {
            loginSuccess();
        }
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


    private void loginSuccess()
    {
        loginSuccessListener.onLoginSuccess();
    }

}
