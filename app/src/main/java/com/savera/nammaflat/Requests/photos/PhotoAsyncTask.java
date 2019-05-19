package com.savera.nammaflat.Requests.photos;

import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.Requests.AsyncTaskListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

abstract public class PhotoAsyncTask implements Response.Listener<JSONObject>, Response.ErrorListener {
    protected WeakReference<GoogleAuthActivity> mAuthActivity;
    private AsyncTaskListener mAsyncTaskListener;
    protected ProgressDialog progressBar;

    public PhotoAsyncTask(GoogleAuthActivity authActivity) {
        this.mAuthActivity = new WeakReference<>(authActivity);
        mAsyncTaskListener = authActivity;
        progressBar = new ProgressDialog(authActivity);
        progressBar.setMessage("Reading Data..");
    }

    public void SetAsyncTaskListner(AsyncTaskListener asyncTaskListener) {
        mAsyncTaskListener = asyncTaskListener;
    }

    public void ExecutePhotosQuery() {
        progressBar.show();
        try {
            RunPhotosQuery();
        } catch (IOException e) {
            Log.d(mAuthActivity.get().TAG, "Photos Query Failed");
        }
    }

    protected void NotifyAsyncTaskListeners() {
        if (mAsyncTaskListener != null) {
            mAsyncTaskListener.OnFBQueryComplete();
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        NotifyAsyncTaskListeners();
        progressBar.hide();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NotifyAsyncTaskListeners();
        progressBar.hide();

    }

    abstract protected void RunPhotosQuery() throws IOException;
}
