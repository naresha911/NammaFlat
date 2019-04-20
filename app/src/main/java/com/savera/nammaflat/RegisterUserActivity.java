package com.savera.nammaflat;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import static com.savera.nammaflat.GoogleAuthActivity.RETURN_CODES.RETURN_Sucess;

public class RegisterUserActivity extends GoogleAuthActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner mBlock;
    Spinner mFlatNumber;
    EditText mName;
    Button mEmail;
    TextView mEmailTextView;
    EditText mPhone;
    Button mRegister;
    QUERY_STAGE mQueryStage;

    private enum QUERY_STAGE {
        QUERY_EMAIL,
        QUERY_REGISTER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        TAG = "ADDUSER_ACTIVITY";

        mBlock = findViewById(R.id.block_register_spinner);
        mFlatNumber = findViewById(R.id.flat_number_register_spinner);
        mName = findViewById(R.id.name_register);
        mEmail = findViewById(R.id.email_register);
        mEmailTextView = findViewById(R.id.email_text_view_register);
        mPhone = findViewById(R.id.phone_register);
        mRegister = findViewById(R.id.register_owner);

        mBlock.setOnItemSelectedListener(this);
        mRegister.setOnClickListener(this);
        mEmail.setOnClickListener(this);

        InitializeFlatsSpinner(0);
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        if(mQueryStage == QUERY_STAGE.QUERY_EMAIL) {
            mEmailTextView.setText(googleAccountName);
            mEmailTextView.setVisibility(View.VISIBLE);
        } else if(mQueryStage == QUERY_STAGE.QUERY_REGISTER) {


        }
        return RETURN_Sucess;
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
        if(v == mEmail)
        {
            mQueryStage = QUERY_STAGE.QUERY_EMAIL;
        } else if( v == mRegister) {
            if (!ValidateFields())
                return;
            mQueryStage = QUERY_STAGE.QUERY_REGISTER;
        }
        TriggerDatabaseQuery();
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

    private boolean IsUserRegistered() {
        return false;
    }
}
