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

import android.content.Intent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import android.widget.Toast;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.UserInfo;
import edu.cmu.foreverhungry.services.remote.*;




public class ForgotPasswordFragment extends LoginFragmentBase implements OnClickListener {


    private TextView instructionsTextView;
    private EditText emailField;
    private Button submitButton;
    private boolean emailSent = false;
    private ForgotPasswordSuccessListener forgotPasswordSuccessListener;


    private static final String TAG = "ForgotPasswordFragment";
    private String email = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.forgot_password_fragment,
                parent, false);
        instructionsTextView = (TextView) v
                .findViewById(R.id.login_help_instructions);
        emailField = (EditText) v.findViewById(R.id.login_help_username_input);
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
        email = emailField.getText().toString();
        new PasswordRetrieval().execute();

    }

    private class PasswordRetrieval extends AsyncTask {
        private boolean success = false;
        private String password = null;

        @Override
        protected Object doInBackground(Object[] params) {
            String result;
            ObjectInputStream in = null;
            ObjectOutputStream out = null;

            try {
                String ServerIP = getResources().getString(R.string.ServerIP);
                Integer port = Integer.valueOf(getResources().getString(R.string.ServerPort));
                Log.v(TAG, ServerIP);
                Log.v(TAG, port.toString());
                Socket socket = new Socket(ServerIP, port);
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject("lostpassword");
                out.writeObject(email);
                result = (String)in.readObject();
                Log.v(TAG,"result is "+result);
                if(result.equals("SUCCESS")) {
                    success = true;
                    password = (String)in.readObject();
                    Log.v(TAG,password);
                    return null;
                }
            } catch (Exception e) {
                Log.d("ERROR", e.getMessage());
                return false;
            }

            return null;
        }

        protected void onPostExecute(final Object loginResults) {
            Log.v(TAG,"success is "+success);
            if(success == false) {
                showToast("Email not found. Try again!");
                return;
            }

            Mail m = new Mail("foreverhungry2015@gmail.com", "root1234");
            String [] toArr = {email};
            m.setTo(toArr);
            m.setFrom("foreverhungry2015@gmail.com");
            m.setSubject("Password info");
            m.setBody(password);

            try {

                if(m.send()) {
                    showToast("Email was sent successfully.");
                } else {

                    showToast("Some issue with the email provided." +
                                        "Email was not sent successfully.");

                }
            } catch(Exception e) {
                Log.v("MailApp", "Could not send email", e);
            }

            forgotPasswordSuccessListener.onClickSuccess();
        }
    }
}
