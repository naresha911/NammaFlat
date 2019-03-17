package com.savera.nammaflat;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static java.lang.Boolean.TRUE;

//https://www.youtube.com/watch?v=uJDLT8nh2ps
//https://www.youtube.com/watch?v=VUPM387qyrw

public class MainActivity extends AppCompatActivity
{
    CardView mRequestsCardView;
    CardView mPropertyCardView;
    CardView mEventsCardView;
    CardView mPhotosCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        mRequestsCardView = findViewById(R.id.Requests);
        mPropertyCardView = findViewById(R.id.Property);
        mEventsCardView = findViewById(R.id.Events);
        mPhotosCardView = findViewById(R.id.Photos);

        mRequestsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Requests", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ServiceRequests.class));
            }
        });

        mPropertyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Property", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ServiceRequests.class));
            }
        });

        mEventsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Events", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ServiceRequests.class));
            }
        });

        mPhotosCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Photos", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ServiceRequests.class));
            }
        });




        //startActivity(new Intent(MainActivity.this, SignupActivity.class));

    }



}
