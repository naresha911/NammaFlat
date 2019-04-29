package com.savera.nammaflat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.Adapters.RecyclerViewItemClickListener;
import com.savera.nammaflat.Requests.Firebase.FBQueryGetDataAsyncTask;
import com.savera.nammaflat.modal.FBModalEntityList;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.util.ArrayList;

import static com.savera.nammaflat.Constants.REQUEST_SERVICE_REQUEST_ADD;
import static java.lang.Boolean.TRUE;

public class ShowServiceRequests extends GoogleAuthActivity implements RecyclerViewItemClickListener {

    private RecyclerView mRecyclerView;
    private FBModalEntityList<ServiceRequestModal> mServiceRequestsEntries;
    private ServiceReqAdapter mAdapter;
    private FBQueryGetDataAsyncTask mGetDataAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_requests);

        mRecyclerView = findViewById(R.id.recyclerViewServiceRequests);
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        mGetDataAsyncTask = new FBQueryGetDataAsyncTask(this);
        mGetDataAsyncTask.RunGetAllDocumentsQuery(Constants.SR_COLLECTION);
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
        startActivityForResult(new Intent(this, ServiceRequestForm.class), REQUEST_SERVICE_REQUEST_ADD);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_SERVICE_REQUEST_ADD: {
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    ServiceRequestModal requestModal = (ServiceRequestModal) data.getSerializableExtra(Constants.EXTRAS_ACTIVITY_RESULT_MODAL);
                    if(requestModal == null){
                        Toast.makeText(this, "Failed to add request", Toast.LENGTH_LONG);
                    }
                    mServiceRequestsEntries.Add(requestModal);
                    mAdapter.notifyDataSetChanged();
                }

            }
            break;
        }
    }

    @Override
    public void OnFBQueryComplete() {
        if(!mGetDataAsyncTask.IsTaskSuccessfull())
            return;

        if(mServiceRequestsEntries != null)
            mServiceRequestsEntries.Reset();
        else
            mServiceRequestsEntries = new FBModalEntityList();

        ArrayList<Object> resultObjs = mGetDataAsyncTask.getResults(ServiceRequestModal.class.getName());
        if(!resultObjs.isEmpty())
            mServiceRequestsEntries.Add(resultObjs);

        if(!mServiceRequestsEntries.IsEmpty()) {
            mAdapter = new ServiceReqAdapter(this, mServiceRequestsEntries);
            mRecyclerView.setHasFixedSize(TRUE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
        }

    }
}
