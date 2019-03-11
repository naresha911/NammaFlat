package com.savera.nammaflat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ServiceReqAdapter adapter;
//https://www.youtube.com/watch?v=uJDLT8nh2ps
    List<ServiceRequestModal> serviceRequestList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceRequestList = new ArrayList<>();
        serviceRequestList.add(new ServiceRequestModal("Ticket title", "","Pending", 1));
        serviceRequestList.add(new ServiceRequestModal("Ticket title", "","Pending", 1));
        serviceRequestList.add(new ServiceRequestModal("Ticket title", "","Pending", 1));
        serviceRequestList.add(new ServiceRequestModal("Ticket title", "","Pending", 1));
        serviceRequestList.add(new ServiceRequestModal("Ticket title", "","Pending", 1));

        recyclerView = findViewById(R.id.recyclerViewServiceRequests);
        recyclerView.setHasFixedSize(TRUE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ServiceReqAdapter(this, serviceRequestList));
        //startActivity(new Intent(MainActivity.this, SignupActivity.class));

    }
}
