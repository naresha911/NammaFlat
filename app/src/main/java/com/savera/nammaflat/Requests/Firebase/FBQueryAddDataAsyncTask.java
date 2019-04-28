package com.savera.nammaflat.Requests.Firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.savera.nammaflat.GoogleAuthActivity;

import java.io.IOException;

public class FBQueryAddDataAsyncTask extends FirebaseAsyncTask implements OnCompleteListener<DocumentReference> {

    private String mCollection;
    private String mDocument;
    private Object mModalObject;

    public FBQueryAddDataAsyncTask(GoogleAuthActivity authActivity) {
        super(authActivity);
    }

    public void SetQueryData(String collection, String document, Object modalObj) {
        mCollection = collection;
        mDocument = document;
        mModalObject = modalObj;
    }

    @Override
    protected void RunFBQuery() throws IOException {
        CollectionReference colRef = mFBDatabase.collection(mCollection);

        colRef.add(mModalObject).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<DocumentReference> task) {
        super.onComplete(task.isSuccessful());
    }
}
