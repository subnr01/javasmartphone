package edu.cmu.foreverhungry.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.cmu.foreverhungry.R;
import edu.cmu.foreverhungry.model.UserInfo;
import edu.cmu.foreverhungry.services.remote.Mail;

public class ChangePassword extends Activity implements View.OnClickListener {

    private EditText passwordField;
    private EditText confirmPasswordField;
    private Button submitButton;
    private String password;
    private String passwordAgain;

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

        password = passwordField.getText().toString();
        passwordAgain = confirmPasswordField.getText().toString();

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
        new PasswordChange().execute();
        return;

    }

    protected void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }



    private class PasswordChange extends AsyncTask {
        private boolean success = false;
        //private String password = null;

        @Override
        protected Object doInBackground(Object[] params) {
            String result;
            ObjectInputStream in = null;
            ObjectOutputStream out = null;

            try {
                String ServerIP = getResources().getString(R.string.ServerIP);
                Integer port = Integer.valueOf(getResources().getString(R.string.ServerPort));
                Log.v("subbu3", ServerIP);
                Log.v("subbu3", port.toString());
                Socket socket = new Socket(ServerIP, port);
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject("changePassword");

                out.writeObject(passwordAgain);
                out.writeObject(UserInfo.getInstance().getEmail());

                result = (String)in.readObject();
                Log.v("subbu3","result is "+result);
                if(result.equals("SUCCESS")) {
                    success = true;
                    return null;
                }
            } catch (Exception e) {
                Log.d("ERROR", e.getMessage());
                return false;
            }

            return null;
        }

        protected void onPostExecute(final Object loginResults) {
            Log.v("subbu3", "success is " + success);
            if(success == false) {
                showToast("Email not found. Try again!");
                return;
            }
            showToast("Password changed successfully");
            UserInfo.clearUserInfo();
            Intent intent = new Intent(getApplicationContext(), LoginScreenActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

}

