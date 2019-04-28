package com.savera.nammaflat.Requests.Firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.modal.FBModal;

import java.io.IOException;
import java.util.ArrayList;

public class FBQueryAddDataAsyncTask extends FirebaseAsyncTask {

    private String mCollection;
    private Object mModalObject;

    public FBQueryAddDataAsyncTask(GoogleAuthActivity authActivity) {
        super(authActivity);
    }

    @Override
    public ArrayList<Object> getResults(String sClassName) {
        /*if(mResultTask.getResult() instanceof DocumentReference) {

            DocumentReference document = (DocumentReference) mResultTask.getResult();
            ArrayList<Object> resultsObjs = new ArrayList<>();
            FBModal obj = (FBModal) GetObjectFromClassName(sClassName);
            if(obj != null){
                obj.FillData(document.getData());
                resultsObjs.add(obj);
            }
        }*/
        return null;
    }

    public void AddNewDocument(String collection, Object modalObject) {
        mCollection = collection;
        mModalObject = modalObject;
        ExecuteFBQuery();
    }

    @Override
    protected void RunFBQuery() throws IOException {
        CollectionReference colRef = mFBDatabase.collection(mCollection);
        colRef.add(mModalObject).addOnCompleteListener(this); //DocumentReference
    }

}
