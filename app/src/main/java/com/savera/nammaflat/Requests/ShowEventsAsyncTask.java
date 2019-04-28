package com.savera.nammaflat.Requests;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.savera.nammaflat.Constants;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.modal.EventModal;

import java.io.IOException;
import java.util.ArrayList;

public class ShowEventsAsyncTask extends GoogleAsyncTask {
    public ShowEventsAsyncTask(GoogleAuthActivity authActivity) {
        super(authActivity);
    }

    @Override
    protected void Run() throws IOException {
        CollectionReference colRef = mDB.collection(Constants.EV_COLLECTION);
        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Object> resultObjs = new ArrayList<>();
                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                        EventModal eventModal = new EventModal();
                        eventModal.FillData(documentSnapshot.getData());
                        resultObjs.add(eventModal);
                        Log.d(mAuthActivity.get().TAG, documentSnapshot.getId(), task.getException());
                    }

                    NotifyAsyncTaskListeners(resultObjs);
                } else {
                    Log.d(mAuthActivity.get().TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }
}
