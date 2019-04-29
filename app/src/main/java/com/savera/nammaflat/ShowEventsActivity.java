package com.savera.nammaflat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.savera.nammaflat.Adapters.EventsAdapter;
import com.savera.nammaflat.Requests.Firebase.FBQueryGetDataAsyncTask;
import com.savera.nammaflat.modal.EventModal;
import com.savera.nammaflat.modal.FBModalEntityList;

import java.util.ArrayList;

import static com.savera.nammaflat.Constants.REQUEST_SERVICE_REQUEST_ADD;
import static java.lang.Boolean.TRUE;

public class ShowEventsActivity extends GoogleAuthActivity {
    private RecyclerView mRecyclerView;
    private EventsAdapter mEventsAdapter;
    private FBQueryGetDataAsyncTask mGetDataAsyncTask;
    private FBModalEntityList<EventModal> mEventEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        mRecyclerView = findViewById(R.id.ev_recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*AsyncLoadSheets loadSheetsReq = new AsyncLoadSheets(this, SheetsConstants.FLAT_REQUEST_SHEET, "Sheet1!A1:B4");
        loadSheetsReq.execute();*/
        TriggerDatabaseQuery();
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        mGetDataAsyncTask = new FBQueryGetDataAsyncTask(this);
        mGetDataAsyncTask.RunGetAllDocumentsQuery(Constants.EV_COLLECTION);
        return RETURN_CODES.RETURN_Sucess;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_SERVICE_REQUEST_ADD: {
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    EventModal eventModal = (EventModal) data.getSerializableExtra(Constants.EXTRAS_ACTIVITY_RESULT_MODAL);
                    if(eventModal == null){
                        Toast.makeText(this, "Failed to add request", Toast.LENGTH_LONG);
                        return;
                    }
                    mEventEntries.Add(eventModal);
                    mEventsAdapter.notifyDataSetChanged();
                }
            }
            break;
        }
    }

    @Override
    public void OnFBQueryComplete() {
        if(!mGetDataAsyncTask.IsTaskSuccessfull())
            return;

        if(mEventEntries != null)
            mEventEntries.Reset();
        else
            mEventEntries = new FBModalEntityList();

        ArrayList<Object> resultObjs = mGetDataAsyncTask.getResults(EventModal.class.getName());
        if(!resultObjs.isEmpty())
            mEventEntries.Add(resultObjs);

        if(!mEventEntries.IsEmpty()) {
            mEventsAdapter = new EventsAdapter(this, mEventEntries);
            mRecyclerView.setHasFixedSize(TRUE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mEventsAdapter);
        }

    }

}
