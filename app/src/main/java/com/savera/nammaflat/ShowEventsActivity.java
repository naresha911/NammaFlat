package com.savera.nammaflat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ShowEventsActivity extends GoogleAuthActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        //ShowEventsAsyncTask eventsAsyncTask = new ShowEventsAsyncTask(this);
        //eventsAsyncTask.execute();
        return RETURN_CODES.RETURN_Sucess;
    }

    @Override
    public void OnFBQueryComplete() {

    }

}
