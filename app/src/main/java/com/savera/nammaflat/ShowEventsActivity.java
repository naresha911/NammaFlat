package com.savera.nammaflat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.savera.nammaflat.Requests.AsyncTaskListener;
import com.savera.nammaflat.Requests.ShowEventsAsyncTask;

import java.util.ArrayList;

public class ShowEventsActivity extends GoogleAuthActivity implements AsyncTaskListener {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        ShowEventsAsyncTask eventsAsyncTask = new ShowEventsAsyncTask(this);
        eventsAsyncTask.execute();
        return RETURN_CODES.RETURN_Sucess;
    }

    @Override
    public void OnFBQueryComplete() {

    }

    @Override
    public void SetFBResult(ArrayList<Object> resultObjs) {

    }
}
