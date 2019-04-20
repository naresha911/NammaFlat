package com.savera.nammaflat.Requests;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.savera.nammaflat.GoogleAuthActivity;

import java.io.IOException;
import java.util.Map;

public class FirebaseReadData extends GoogleAsyncTask {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String mCollection;
    String mDocName;
    Map<String, String> mResults;

    public FirebaseReadData(GoogleAuthActivity authActivity) {
        super(authActivity);
    }

    public void SetQueryInfo(String sCollection, String sDocName) {
        mCollection = sCollection;
        mDocName = sDocName;
    }

    @Override
    protected void Run() throws IOException {
        DocumentReference docRef = db.collection(mCollection).document(mDocName);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(mAuthActivity.TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(mAuthActivity.TAG, "No such document");
                    }
                } else {
                    Log.d(mAuthActivity.TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
