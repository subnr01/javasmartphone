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
import android.widget.TextView;
import edu.cmu.foreverhungry.R;




public class ForgotPasswordFragment extends LoginFragmentBase implements OnClickListener {


    private TextView instructionsTextView;
    private EditText emailField;
    private Button submitButton;
    private boolean emailSent = false;
    private ForgotPasswordSuccessListener forgotPasswordSuccessListener;


    private static final String TAG = "ForgotPasswordFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.forgot_password_fragment,
                parent, false);
        instructionsTextView = (TextView) v
                .findViewById(R.id.login_help_instructions);
        emailField = (EditText) v.findViewById(R.id.login_help_email_input);
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
        if (!emailSent) {
            String email = emailField.getText().toString();
            if (email.length() == 0) {
                showToast(R.string.no_email_toast);
            } else {
                emailSent = true;
            }
        } else {
            forgotPasswordSuccessListener.onClickSuccess();
        }
    }


}
