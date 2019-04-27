package com.savera.nammaflat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.savera.nammaflat.Utils.SharedPrefrncsUtils;
import com.savera.nammaflat.modal.ServiceRequestModal;

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
                startActivity(new Intent(MainActivity.this, ShowServiceRequests.class));
                //ServiceRequestForm servicesForm = new ServiceRequestForm();
                //servicesForm.show(getSupportFragmentManager(), "example dialog");
            }
        });

        mPropertyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Property", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ShowServiceRequests.class));
            }
        });

        mEventsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Events", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ShowServiceRequests.class));
            }
        });

        mPhotosCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Display Photos", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, ShowServiceRequests.class));
            }
        });

    }

}
