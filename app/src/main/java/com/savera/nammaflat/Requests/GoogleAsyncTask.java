package com.savera.nammaflat.Requests;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.savera.nammaflat.Constants;
import com.savera.nammaflat.GoogleAuthActivity;

import java.io.IOException;

abstract public class GoogleAsyncTask extends AsyncTask<Void, Void, Boolean> {

    protected final GoogleAuthActivity mAuthActivity;
    protected ProgressDialog progressBar;
    protected Exception mLastError = null;
    private static final String TAG = "GoogleAsyncTask";

    private String googleAuthToken;
    private String googleAccountName;

    public GoogleAsyncTask(GoogleAuthActivity authActivity) {
        mAuthActivity = authActivity;
        progressBar = new ProgressDialog(authActivity);
        progressBar.setMessage("Reading Data..");
        googleAccountName = mAuthActivity.googleAccountName;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Log.d(TAG,"Requesting token for account: " +
                    googleAccountName);
            googleAuthToken = GoogleAuthUtil.getToken(mAuthActivity,
                    googleAccountName, Constants.GPHOTOS_SCOPE);

            Log.d(TAG, "Received Token: " + googleAuthToken);
            //GDController.setAPIToken(googleAuthToken);
            //setReadyToLoadImages();
            this.Run();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (UserRecoverableAuthException e) {
            mAuthActivity.startActivityForResult(e.getIntent(), Constants.REQ_SIGN_IN_REQUIRED);
        } catch (GoogleAuthException e) {
            Log.e(TAG, e.getMessage());
        }

        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progressBar.hide();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    abstract protected void Run() throws IOException;
}
