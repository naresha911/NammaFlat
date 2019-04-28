package com.savera.nammaflat.Requests.Firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.Requests.AsyncTaskListener;
import com.savera.nammaflat.modal.FBModal;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

abstract public class FirebaseAsyncTask implements OnCompleteListener {

    protected FirebaseFirestore mFBDatabase;
    private WeakReference<GoogleAuthActivity> mAuthActivity;
    private AsyncTaskListener mAsyncTaskListener;
    protected ProgressDialog progressBar;
    Task mResultTask;

    public FirebaseAsyncTask(GoogleAuthActivity authActivity) {
        this.mAuthActivity = new WeakReference<>(authActivity);
        mFBDatabase = FirebaseFirestore.getInstance();
        mAsyncTaskListener = authActivity;
        progressBar = new ProgressDialog(authActivity);
        progressBar.setMessage("Reading Data..");
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
        if (mAsyncTaskListener != null) {
            mAsyncTaskListener.OnFBQueryComplete();
        }
    }

    protected Object GetObjectFromClassName(String sClassName){
        try {
            return Class.forName(sClassName).newInstance();
        } catch (ClassNotFoundException e) {
            Log.d(mAuthActivity.get().TAG, "Class not found");
        } catch (IllegalAccessException e) {
            Log.d(mAuthActivity.get().TAG, "Illegal Class not found");
        } catch (InstantiationException e) {
            Log.d(mAuthActivity.get().TAG, "Instantiation not found");
        }
        return null;
    }

    public ArrayList<Object> getResults(String sClassName) {
       return null;
    }

    public boolean IsTaskSuccessfull() {
        return mResultTask.isSuccessful();
    }

    abstract protected void RunFBQuery() throws IOException;

    @Override
    public void onComplete(@NonNull Task task) {
        mResultTask = task;
        NotifyAsyncTaskListeners();

        if (mResultTask.isSuccessful()) {
            this.OnSuccess();
        } else {
            this.OnFailure();
        }

        progressBar.hide();
    }
}
