package com.savera.nammaflat;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.Requests.AsyncLoadSheets;
import com.savera.nammaflat.modal.SheetsConstants;

import static java.lang.Boolean.TRUE;

public class ShowServiceRequests extends AuthActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_requests);

        mRecyclerView = findViewById(R.id.recyclerViewServiceRequests);
    }

    @Override
    protected void onStart() {
        super.onStart();

        AsyncLoadSheets loadSheetsReq = new AsyncLoadSheets(this, SheetsConstants.FLAT_REQUEST_SHEET, "Sheet1!A1:B4");
        loadSheetsReq.execute();

        if(!loadSheetsReq.GetRequests().IsEmpty()) {
            mRecyclerView.setHasFixedSize(TRUE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(new ServiceReqAdapter(this, loadSheetsReq.GetRequests()));
        }
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
}
