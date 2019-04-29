package com.savera.nammaflat.Requests.Sheets;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.savera.nammaflat.AuthActivity;
import com.savera.nammaflat.modal.FBModalEntityList;

import java.io.IOException;

abstract class SheetsAsyncTask extends AsyncTask<Void, Void, Boolean> {

    protected final AuthActivity mAuthActivity;
    protected ProgressDialog progressBar;
    protected com.google.api.services.sheets.v4.Sheets mService = null;
    protected Exception mLastError = null;
    protected String mSpreadSheetId;
    protected String mRange;
    protected FBModalEntityList mServiceRequests;


    public SheetsAsyncTask(AuthActivity authActivity, String spreadSheetId, String range) {
        mAuthActivity = authActivity;
        progressBar = new ProgressDialog(authActivity);
        progressBar.setMessage("Reading Data..");

        mSpreadSheetId = spreadSheetId;
        mRange = range;

        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                transport, jsonFactory, mAuthActivity.mCredential)
                .setApplicationName("Google Sheets API")
                .build();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            this.Run();
            return true;
        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
            mLastError = availabilityException;
            mAuthActivity.showGooglePlayServicesAvailabilityErrorDialog(
                    availabilityException.getConnectionStatusCode());
            return false;
        } catch (UserRecoverableAuthIOException userRecoverableException) {
            mLastError = userRecoverableException;
            mAuthActivity.startActivityForResult(
                    userRecoverableException.getIntent(), mAuthActivity.REQUEST_AUTHORIZATION);
            return false;
        } catch (IOException e) {
            mLastError = e;
            //Utils.logAndShow(activity, CalendarSampleActivity.TAG, e);
            return false;
        }
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

    public FBModalEntityList GetRequests() {
        if(mServiceRequests.IsEmpty())
            return null;

        return mServiceRequests;
    }

    abstract protected void Run() throws IOException;
}
