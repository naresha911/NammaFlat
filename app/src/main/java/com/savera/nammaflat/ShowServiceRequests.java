package com.savera.nammaflat;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.Adapters.ServiceRequestItemClickListener;
import com.savera.nammaflat.Requests.AsyncLoadSheets;
import com.savera.nammaflat.Requests.GoogleAsyncTask;
import com.savera.nammaflat.modal.ServiceRequestEntries;
import com.savera.nammaflat.modal.ServiceRequestModal;
import com.savera.nammaflat.modal.SheetsConstants;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static java.lang.Boolean.TRUE;

public class ShowServiceRequests extends GoogleAuthActivity implements ServiceRequestItemClickListener {

    private RecyclerView mRecyclerView;
    private ServiceRequestEntries mServiceRequestsEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_requests);

        mRecyclerView = findViewById(R.id.recyclerViewServiceRequests);
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        FirebaseRetrieveServiceRequests retrieveServiceRequests = new FirebaseRetrieveServiceRequests(this);
        retrieveServiceRequests.execute();
        return RETURN_CODES.RETURN_Sucess;
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*AsyncLoadSheets loadSheetsReq = new AsyncLoadSheets(this, SheetsConstants.FLAT_REQUEST_SHEET, "Sheet1!A1:B4");
        loadSheetsReq.execute();*/
        TriggerDatabaseQuery();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean OnItemClickListener(int position, View v) {
        Intent launchSRFormActivity = new Intent(this, ServiceRequestForm.class);
        launchSRFormActivity.putExtra("model_data", mServiceRequestsEntries.GetAt(position));
        startActivity(launchSRFormActivity);
        return false;
    }

    public void OnAddServiceRequest(View view) {
        ServiceRequestForm servicesForm = new ServiceRequestForm();
        servicesForm.show(getSupportFragmentManager(), "example dialog");
    }

    private class FirebaseRetrieveServiceRequests extends GoogleAsyncTask {

        public FirebaseRetrieveServiceRequests(GoogleAuthActivity authActivity) {
            super(authActivity);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        }

        @Override
        protected void Run() throws IOException {
            mAuthActivity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CollectionReference colRef = mDB.collection(Constants.SR_COLLECTION);
                    colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if(mServiceRequestsEntries != null)
                                    mServiceRequestsEntries.Reset();
                                else
                                    mServiceRequestsEntries = new ServiceRequestEntries();
                                for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                    ServiceRequestModal srModel = new ServiceRequestModal();
                                    srModel.FillData(documentSnapshot.getData());
                                    mServiceRequestsEntries.Add(srModel);
                                    Log.d(mAuthActivity.get().TAG, documentSnapshot.getId(), task.getException());
                                }

                                if(!mServiceRequestsEntries.IsEmpty()) {
                                    mRecyclerView.setHasFixedSize(TRUE);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mAuthActivity.get()));
                                    mRecyclerView.setAdapter(new ServiceReqAdapter(mAuthActivity.get(), mServiceRequestsEntries));
                                }
                            } else {
                                Log.d(mAuthActivity.get().TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
                }
            });


        }
    }
}
