package edu.cmu.foreverhungry.exception;

import android.app.AlertDialog;
import android.util.Log;

/**
 * Created by admin on 11/26/15.
 */
public class AppException {

    private final String ERROR_TITLE = "ERROR:";


    public AppException(String s, AlertDialog.Builder b){
        Log.e(null, s);
        showErrorMessage(s, b);
    }



    /*
    * buildErrorDialog()
    *
    *
    * Show error message
    *
    * */
    private void showErrorMessage(String s, AlertDialog.Builder b){
        // set dialog title & message, and provide Button to dismiss
        b.setTitle(ERROR_TITLE);
        b.setMessage(s);
    }
}
