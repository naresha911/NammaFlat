package com.savera.nammaflat.Requests.Firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.savera.nammaflat.GoogleAuthActivity;

import java.io.IOException;

public class FBQueryGetDataAsyncTask extends FirebaseAsyncTask implements OnCompleteListener<QuerySnapshot> {

    private enum  QUERY_GET_MODE {
        QUERY_GET_ALL_DOCUMENTS,
        QUERY_GET_DOCUMENT
    }

    private String mCollection;
    private String mDocument;
    private QUERY_GET_MODE mQueryMode;

    public FBQueryGetDataAsyncTask(GoogleAuthActivity authActivity) {
        super(authActivity);
        mCollection = null;
        mDocument = null;
        mQueryMode = QUERY_GET_MODE.QUERY_GET_ALL_DOCUMENTS;
    }

    @Override
    protected void RunFBQuery() {
        if(mCollection == null || mCollection.isEmpty())
            return;

        if(mQueryMode == QUERY_GET_MODE.QUERY_GET_ALL_DOCUMENTS) {
            CollectionReference colRef = mFBDatabase.collection(mCollection);
            colRef.get().addOnCompleteListener(this);
        } else if(mQueryMode == QUERY_GET_MODE.QUERY_GET_DOCUMENT) {
            if(mDocument == null || mDocument.isEmpty())
                return;
        }
    }

    public void RunGetAllDocumentsQuery(String collection) {
        mCollection = collection;
        ExecuteFBQuery();
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
            super.onComplete(task.isSuccessful());
    }
}
