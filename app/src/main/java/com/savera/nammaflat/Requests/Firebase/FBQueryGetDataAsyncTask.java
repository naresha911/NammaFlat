package com.savera.nammaflat.Requests.Firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.modal.FBModal;

import java.util.ArrayList;
import java.util.Map;

public class FBQueryGetDataAsyncTask extends FirebaseAsyncTask {

    private enum  QUERY_GET_MODE {
        QUERY_GET_ALL_DOCUMENTS,
        QUERY_GET_DOCUMENT,
        QUERY_GET_DOCUMENT_GIVEN_FIELDS
    }

    private String mCollection;
    private String mDocument;
    private QUERY_GET_MODE mQueryMode;
    private Map<String, String> mFieldValues;

    public FBQueryGetDataAsyncTask(GoogleAuthActivity authActivity) {
        super(authActivity);
        mCollection = null;
        mDocument = null;
        mQueryMode = QUERY_GET_MODE.QUERY_GET_ALL_DOCUMENTS;
    }

    @Override
    public ArrayList<Object> getResults(String sClassName) {
        if(mResultTask.getResult() instanceof QuerySnapshot) {
            ArrayList<Object> resultsObjs = new ArrayList<>();
            Task<QuerySnapshot> querySnapShot = mResultTask;
            for(DocumentSnapshot document : querySnapShot.getResult()) {
                FBModal obj = (FBModal) GetObjectFromClassName(sClassName);
                if(obj == null)
                    continue;
                obj.FillData(document.getData());
                resultsObjs.add(obj);
            }

            return resultsObjs;
        } else if(mResultTask.getResult() instanceof DocumentSnapshot) {

            DocumentSnapshot document = (DocumentSnapshot) mResultTask.getResult();
            ArrayList<Object> resultsObjs = new ArrayList<>();
            FBModal obj = (FBModal) GetObjectFromClassName(sClassName);
            if(obj != null){
                obj.FillData(document.getData());
                resultsObjs.add(obj);
            }
        }
        return null;
    }

    @Override
    protected void RunFBQuery() {
        if(mCollection == null || mCollection.isEmpty())
            return;

        CollectionReference colRef = mFBDatabase.collection(mCollection);
        if(mQueryMode == QUERY_GET_MODE.QUERY_GET_ALL_DOCUMENTS) {
            colRef.get().addOnCompleteListener(this);//QuerySnapShot
        } else if(mQueryMode == QUERY_GET_MODE.QUERY_GET_DOCUMENT) {
            if(mDocument == null || mDocument.isEmpty())
                return;
            colRef.document(mDocument).get().addOnCompleteListener(this); //DocumentSnapShot
        } else if(mQueryMode == QUERY_GET_MODE.QUERY_GET_DOCUMENT_GIVEN_FIELDS) {
            Query q = null;
            for (Map.Entry<String, String> entry : mFieldValues.entrySet()) {
                q = colRef.whereEqualTo(entry.getKey(), entry.getValue());
            }
            q.get().addOnCompleteListener(this); //QuerySnapShot
        }
    }

    public void RunGetAllDocumentsQuery(String collection) {
        mCollection = collection;
        mQueryMode = QUERY_GET_MODE.QUERY_GET_ALL_DOCUMENTS;
        ExecuteFBQuery();
    }

    public void RunGetAllDocumentsGivenFields(String collection, Map<String, String> fieldAndValuePairs) {
        mCollection = collection;
        mFieldValues = fieldAndValuePairs;
        mQueryMode = QUERY_GET_MODE.QUERY_GET_DOCUMENT_GIVEN_FIELDS;
        ExecuteFBQuery();
    }
}
