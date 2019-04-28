package com.savera.nammaflat.Requests.Firebase;

import android.app.ProgressDialog;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.Requests.AsyncTaskListener;

import java.io.IOException;
import java.lang.ref.WeakReference;

abstract public class FirebaseAsyncTask {

    protected FirebaseFirestore mFBDatabase;
    private WeakReference<GoogleAuthActivity> mAuthActivity;
    private AsyncTaskListener mAsyncTaskListener;
    protected ProgressDialog progressBar;

    public FirebaseAsyncTask(GoogleAuthActivity authActivity) {
        this.mAuthActivity = new WeakReference<> (authActivity);
        mFBDatabase = FirebaseFirestore.getInstance();
    }

    public void SetAsyncTaskListner(AsyncTaskListener asyncTaskListener) {
        mAsyncTaskListener = asyncTaskListener;
    }

    public void ExecuteFBQuery() {
        progressBar.show();
        try {
            RunFBQuery();
        } catch (IOException e) {
            Log.d(mAuthActivity.get().TAG, "FB Query Failed");
        }
    }

    protected void OnSuccess() {

    }

    protected void OnFailure() {

    }

    protected void NotifyAsyncTaskListeners() {
        if(mAsyncTaskListener != null) {
            mAsyncTaskListener.OnFBQueryComplete();
        }
    }

    @Override
    public void onComplete(boolean bIsSuccess) {
        NotifyAsyncTaskListeners();

        if (bIsSuccess) {
            this.OnSuccess();
        } else {
            this.OnFailure();
        }

        progressBar.hide();
    }

    abstract protected void RunFBQuery() throws IOException;
}
