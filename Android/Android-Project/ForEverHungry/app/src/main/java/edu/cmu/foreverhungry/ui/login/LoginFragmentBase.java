package edu.cmu.foreverhungry.ui.login;

/**
 * Created by subs on 11/25/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


public class LoginFragmentBase extends Fragment {

    protected void showToast(int id) {
        showToast(getString(id));
    }

    protected void showToast(CharSequence text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    protected boolean isActivityDestroyed() {
        FragmentActivity activity = getActivity();
            return activity == null || ((LoginScreenActivity) activity).isDestroyed();

    }
}
