package com.savera.nammaflat.Requests;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.savera.nammaflat.GoogleAuthActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;

public class FirebaseAddData extends GoogleAsyncTask {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String mCollection;
    String mDocName;
    Map<String, String> mDataToAdd;

    public FirebaseAddData(GoogleAuthActivity authActivity) {
        super(authActivity);
    }

    public void SetQueryInfo(String sCollection, String sDocName, Map<String, String> dataToAdd) {
        mCollection = sCollection;
        mDocName = sDocName;
        mDataToAdd = dataToAdd;
    }

    @Override
    protected void Run() throws IOException {
        db.collection(mCollection).document(mDocName).set(mDataToAdd)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(mAuthActivity.get().TAG, "Successfully Registered");
                        Toast.makeText(mAuthActivity.get(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(mAuthActivity.get().TAG, "Registration Failed", e);
                        Toast.makeText(mAuthActivity.get(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
