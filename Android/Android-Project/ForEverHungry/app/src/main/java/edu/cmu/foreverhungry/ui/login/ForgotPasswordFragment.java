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
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.cmu.foreverhungry.R;




public class ForgotPasswordFragment extends LoginFragmentBase implements OnClickListener {


    private TextView instructionsTextView;
    private EditText usernameField;
    private Button submitButton;
    private boolean emailSent = false;
    private ForgotPasswordSuccessListener forgotPasswordSuccessListener;


    private static final String TAG = "ForgotPasswordFragment";
    private String username = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.forgot_password_fragment,
                parent, false);
        instructionsTextView = (TextView) v
                .findViewById(R.id.login_help_instructions);
        usernameField = (EditText) v.findViewById(R.id.login_help_username_input);
        submitButton = (Button) v.findViewById(R.id.login_help_submit);
        submitButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        if (activity instanceof ForgotPasswordSuccessListener) {
            forgotPasswordSuccessListener = (ForgotPasswordSuccessListener) activity;
        } else {
            throw new IllegalArgumentException();

        }
    }

    @Override
    public void onClick(View v) {
        username = usernameField.getText().toString();
        new PasswordRetrieval().execute();
        //forgotPasswordSuccessListener.onClickSuccess();
    }

    private class PasswordRetrieval extends AsyncTask {
        private boolean success = false;
        private String password = null;

        @Override
        protected Object doInBackground(Object[] params) {
            String result = null;
            ObjectInputStream in = null;
            ObjectOutputStream out = null;

            try {
                Socket socket = new Socket("10.0.0.4", 6666);
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject("lostpassword");
                out.writeObject(username);
                result = (String)in.readObject();

                if(result.equals("SUCCESS")) {
                    success = true;
                    password = (String)in.readObject();
                    return null;
                }
            } catch (Exception e) {
                Log.d("ERROR", e.getMessage());
                return false;
            }

            return null;
        }

        protected void onPostExecute(final Object loginResults) {
            if(success == true) {
                showToast("Your password is: " + password);
                return;
            }

            showToast("Username not found. Try again!");
        }
    }
}
