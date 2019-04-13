package com.savera.nammaflat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Vector;

public class Register extends AppCompatActivity {

    Spinner mBlock;
    Spinner mFlatNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Vector<Integer> arraySpinner = new Vector<>();
        for(int flatNo = 101; flatNo <= 360; ++flatNo)
            arraySpinner.add(flatNo);

        Spinner s = (Spinner) findViewById(R.id.flat_number);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }
}
