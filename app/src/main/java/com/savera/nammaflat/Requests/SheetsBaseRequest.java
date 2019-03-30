package com.savera.nammaflat.Requests;

import android.os.AsyncTask;

import com.google.android.gms.auth.api.Auth;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.savera.nammaflat.AuthActivity;
import com.savera.nammaflat.MyApplication;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.savera.nammaflat.MyApplication.mCredential;

public class SheetsBaseRequest{
    private AuthActivity mAuthActivity;

    public SheetsBaseRequest(AuthActivity authActivity) {
        mAuthActivity = authActivity;
    }

    public int getAllFlatRequests()
    {
        SheetsBaseRequest.MakeRequestTask makeRequestTask = new SheetsBaseRequest.MakeRequestTask(MyApplication.FLAT_REQUEST_SHEET, "Sheet1!A1:B4");
        if(makeRequestTask != null)
        {
            if(makeRequestTask.execute() == null)
                return 1;
        }
        return 0;
    }


    /**
     * An asynchronous task that handles the Google Sheets API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask extends AsyncTask<Void, Void, List<List<String>>> {
        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;

        private String mSpreadSheetId;
        private String mRange;

        MakeRequestTask(String spreadSheetId, String range) {
            mSpreadSheetId = spreadSheetId;
            mRange = range;

            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, MyApplication.mCredential)
                    .setApplicationName("Google Sheets API")
                    .build();
        }

        /**
         * Background task to call Google Sheets API.
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<List<String>> doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                MyApplication.mCredential.setSelectedAccount(null);
                mAuthActivity.startActivityForResult(
                        ((UserRecoverableAuthIOException) mLastError).getIntent(),
                        AuthActivity.REQUEST_AUTHORIZATION);
               // mAuthActivity.startActivityForResult();
                return null;
            }
        }

        /**
         * Fetch a list of names and majors of students in a sample spreadsheet:
         * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
         * @return List of names and majors
         * @throws IOException
         */
        private List<List<String>> getDataFromApi() throws IOException {
            List<List<String>> results = new ArrayList<>();

            Sheets.Spreadsheets.Values.Get request =
                    this.mService.spreadsheets().values().get(mSpreadSheetId, mRange);

            ValueRange response = request.execute();
            List<List<Object>> values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    if(!row.isEmpty()) {
                        List<String> colStrs = new ArrayList<>();
                        for (Object col : row) {
                            colStrs.add(col.toString());
                        }
                        results.add(colStrs);
                    }
                }
            }
            return results;
        }



        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(List<List<String>> output) {


        }

        @Override
        protected void onCancelled() {

        }
    }


}
