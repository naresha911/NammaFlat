package com.savera.nammaflat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.savera.nammaflat.Requests.AsyncTaskListener;
import com.savera.nammaflat.Requests.Firebase.FBQueryGetDataAsyncTask;
import com.savera.nammaflat.Requests.GoogleAsyncTask;
import com.savera.nammaflat.Utils.SharedPrefrncsUtils;
import com.savera.nammaflat.modal.UserModal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.savera.nammaflat.GoogleAuthActivity.RETURN_CODES.RETURN_Sucess;

public class RegisterUserActivity extends GoogleAuthActivity implements View.OnClickListener {

    private Spinner mBlock;
    private Spinner mFlatNumber;
    private EditText mName;
    private Button mEmail;
    private TextView mEmailTextView;
    private EditText mPhone;
    private Button mRegister;
    private QUERY_STAGE mQueryStage;
    private FBQueryGetDataAsyncTask mGetDataTask;

    private enum QUERY_STAGE {
        QUERY_EMAIL,
        QUERY_REGISTER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        TAG = "ADDUSER_ACTIVITY";

        mEmail = findViewById(R.id.email_register);
        mEmailTextView = findViewById(R.id.email_text_view_register);
        mPhone = findViewById(R.id.phone_register);
        mRegister = findViewById(R.id.register_owner);

        mRegister.setOnClickListener(this);
        mEmail.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSkipUserRegisterValidation = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSkipUserRegisterValidation = false;
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        if(mQueryStage == QUERY_STAGE.QUERY_EMAIL) {
            mEmailTextView.setText(MyApplication.mGoogleAccountName);
            mEmailTextView.setVisibility(View.VISIBLE);
        } else if(mQueryStage == QUERY_STAGE.QUERY_REGISTER) {
            RegisterUser();
        }
        return RETURN_Sucess;
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
        if(!ValidateEmailField()) {
            return false;
        }

        if(!ValidatePhoneField()) {
            return false;
        }

        return true;
    }

    private boolean ValidateEmailField() {
        String sEmail = mEmailTextView.getText().toString();
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

    private boolean RegisterUser() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constants.USERS_PHONE, mPhone.getText().toString());
        fieldValues.put(Constants.USERS_EMAIL, mEmailTextView.getText().toString());

        mGetDataTask = new FBQueryGetDataAsyncTask(this);
        mGetDataTask.RunGetAllDocumentsGivenFields(Constants.COLLECTION_SAVERA_USERS, fieldValues);
        return true;
    }

    private void SaveUserDataToPreferences(DocumentSnapshot documentSnapshot) {
        SharedPrefrncsUtils.setDefaults(Constants.USERS_ID, documentSnapshot.getId(),this);
        SharedPrefrncsUtils.setDefaults(Constants.USERS_NAME, documentSnapshot.get(Constants.USERS_NAME).toString(),this);
        SharedPrefrncsUtils.setDefaults(Constants.USERS_EMAIL, documentSnapshot.get(Constants.USERS_EMAIL).toString(),this);
        SharedPrefrncsUtils.setDefaults(Constants.USERS_PHONE, documentSnapshot.get(Constants.USERS_PHONE).toString(),this);
        SharedPrefrncsUtils.setDefaults(Constants.USERS_LEVEL, documentSnapshot.get(Constants.USERS_NAME).toString(),this);
    }

    @Override
    public void OnFBQueryComplete() {
        ArrayList<Object> resultObjs = mGetDataTask.getResults(UserModal.class.getName());
       // SaveUserDataToPreferences(resultObjs[0]);
        setResult(RESULT_OK);
        finish();
    }

}
