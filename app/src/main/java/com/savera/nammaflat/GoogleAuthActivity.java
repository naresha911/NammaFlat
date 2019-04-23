package com.savera.nammaflat;

import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.IOException;

abstract public class GoogleAuthActivity extends AppCompatActivity {


    public enum RETURN_CODES {
        RETURN_Sucess,
        RETURN_Fail
    }

    public static String TAG = "GoogleAuthActivity";

    public String googleAccountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    abstract protected RETURN_CODES ExecuteQuery();

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode code indicating the result of the incoming
     *     activity result.
     * @param data Intent (containing result data) returned by incoming
     *     activity result.
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case Constants.REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    isGooglePlayServicesAvailable();
                }
                break;
            case Constants.REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    googleAccountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    TriggerDatabaseQuery();
                } else if (resultCode == RESULT_CANCELED) {
                    Log.d(TAG,"Google account unspecified");
                }
                break;
            case Constants.REQUEST_AUTHORIZATION:
                if (resultCode != RESULT_OK) {
                    chooseGoogleAccount();
                }
                break;
            case Constants.REQ_SIGN_IN_REQUIRED:
                if(resultCode == RESULT_OK) {
                    // We had to sign in - now we can finish off the token request.
                    TriggerDatabaseQuery();
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void TriggerDatabaseQuery() {
        if (googleAccountName != null) {
            SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(Constants.PREF_ACCOUNT_NAME, googleAccountName);
            editor.commit();

            if (isDeviceOnline(getApplicationContext())) {
                this.ExecuteQuery();
            } else {
                Toast.makeText(this, "Device not online", Toast.LENGTH_LONG).show();
            }
        } else {
            chooseGoogleAccount();
        }
    }

    /**
     * Starts an activity in Google Play Services so the user can pick an
     * account. The 4th parameter (force choose) has been set as false so that
     * this is displayed only if multiple accounts are detected on the device
     */
    private void chooseGoogleAccount() {

        String[] accountTypes = new String[]{"com.google"};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);

        Log.d(TAG,"Starting activity for Choosing Account");
        startActivityForResult(intent, Constants.REQUEST_ACCOUNT_PICKER);
    }

    /**
     * Check if network is available.
     * @param context
     * @return
     */
    public static boolean isDeviceOnline(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /**
     * Check that Google Play services APK is installed and up to date. Will
     * launch an error dialog for the user to update Google Play Services if
     * possible.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        final int connectionStatusCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            // Display a dialog showing the connection error
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                            connectionStatusCode, GoogleAuthActivity.this,
                            Constants.REQUEST_GOOGLE_PLAY_SERVICES);
                    dialog.show();
                }
            });
            return false;
        } else if (connectionStatusCode != ConnectionResult.SUCCESS ) {
            return false;
        }
        return true;
    }

}
