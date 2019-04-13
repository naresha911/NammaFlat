package com.savera.nammaflat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner mBlock;
    Spinner mFlatNumber;
    EditText mName;
    EditText mEmail;
    EditText mPhone;
    Button mRegister;

    public static final String TAG = "REGISTER_ACTIVITY";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mBlock = findViewById(R.id.block);
        mFlatNumber = findViewById(R.id.flat_number);
        mName = findViewById(R.id.owner_name);
        mEmail = findViewById(R.id.owner_email);
        mPhone = findViewById(R.id.owner_phone);
        mRegister = findViewById(R.id.register_button);

        mBlock.setOnItemSelectedListener(this);
        mRegister.setOnClickListener(this);

        InitializeFlatsSpinner(0);
    }

    private void InitializeFlatsSpinner(int pos)
    {
        int nFlatStart = (pos*12 + 1);
        int nFlatEnd = pos*12 + 12;

        Vector<Integer> vecFlatsArray = new Vector<>();
        for(int nFloorIndex = 1; nFloorIndex <= 4; ++nFloorIndex) {
            for(int nFlatNumber = nFlatStart; nFlatNumber <= nFlatEnd; ++nFlatNumber) {
                vecFlatsArray.add(nFloorIndex * 100+ nFlatNumber);
            }
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, vecFlatsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFlatNumber.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         InitializeFlatsSpinner(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(!ValidateFields())
            return;

        int pos = mBlock.getSelectedItemPosition();
        String sBlockNumber = mBlock.getItemAtPosition(pos).toString();

        pos = mFlatNumber.getSelectedItemPosition();
        String sFlatNumber = mFlatNumber.getItemAtPosition(pos).toString();

        String sName = mName.getText().toString();
        String sEmail = mEmail.getText().toString();
        String sPhone = mPhone.getText().toString();

        Map<String, String> request = new HashMap<>();
        request.put("name", sName);
        request.put("email", sEmail);
        request.put("phone", sPhone);

        String documentName = Constants.PROJECT_NAME + Constants.SEPERATOR +sBlockNumber + sFlatNumber;

        db.collection("users").document(documentName).set(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Successfully Registered");
                        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Registration Failed", e);
                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean ValidateFields() {
        if(!ValidateNameField()) {
            return false;
        }

        if(!ValidateEmailField()) {
            return false;
        }

        if(!ValidatePhoneField()) {
            return false;
        }

        return true;
    }

    private boolean ValidateNameField() {
        String sName = mName.getText().toString();
        if(sName.isEmpty()) {
            Toast.makeText(this, "Name field is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean ValidateEmailField() {
        String sEmail = mEmail.getText().toString();
        if(sEmail.isEmpty()) {
            Toast.makeText(this, "Email field is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            Toast.makeText(this, "Not a valid email!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean ValidatePhoneField() {
        String sPhnNum = mPhone.getText().toString();
        if(sPhnNum.isEmpty()) {
            Toast.makeText(this, "Phone number is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Patterns.PHONE.matcher(sPhnNum).matches()) {
            Toast.makeText(this, "Not a valid phone number!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
