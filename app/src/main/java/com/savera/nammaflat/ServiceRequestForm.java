package com.savera.nammaflat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.savera.nammaflat.Requests.Firebase.FBQueryAddDataAsyncTask;
import com.savera.nammaflat.Utils.SharedPrefrncsUtils;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.util.HashMap;
import java.util.Map;

public class ServiceRequestForm extends GoogleAuthActivity {
    private Spinner  mReqType;
    private Spinner  mCategory;
    private EditText mTitle;
    private EditText mDescription;
    private Spinner  mStatus;
    private Button   mSubmit;
    private ServiceRequestModal mSrModal;
    private FBQueryAddDataAsyncTask mAddDataAsyncTask;

    private static final String TAG = "RequestForm";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request_form);

        mReqType = findViewById(R.id.srf_type);
        mCategory = findViewById(R.id.srf_category);
        mTitle = findViewById(R.id.srf_title);
        mDescription = findViewById(R.id.srf_description);
        mStatus = findViewById(R.id.srf_status);
        mSubmit = findViewById(R.id.srf_submit);

        //https://www.codeproject.com/Tips/623446/Style-Any-Activity-as-an-Alert-Dialog-in-Android
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        int nReqType = mReqType.getSelectedItemPosition();
        int nCategory = mCategory.getSelectedItemPosition();
        String sTitle = mTitle.getText().toString();
        String sDescrip = mDescription.getText().toString();
        int nStatus = mStatus.getSelectedItemPosition();

        if (sTitle.isEmpty() || sDescrip.isEmpty()) {
            Toast.makeText(this, "Complete all Fields", Toast.LENGTH_SHORT).show();
            return RETURN_CODES.RETURN_Fail;
        }

        String sUserID = SharedPrefrncsUtils.getDefaults(Constants.USERS_ID, this);

        Map<String, Object> request = new HashMap<>();
        request.put(Constants.SR_TYPE, Long.valueOf(nReqType));
        request.put(Constants.SR_CATEGORY, Long.valueOf(nCategory));
        request.put(Constants.SR_TITLE, sTitle);
        request.put(Constants.SR_DESCRIPTION, sDescrip);
        request.put(Constants.SR_STATUS, Long.valueOf(nStatus));
        request.put(Constants.SR_USERID, sUserID);

        mSrModal = new ServiceRequestModal();
        mSrModal.FillData(request);

        mAddDataAsyncTask = new FBQueryAddDataAsyncTask(this);
        mAddDataAsyncTask.AddNewDocument(Constants.SR_COLLECTION, mSrModal);
        mAddDataAsyncTask.ExecuteFBQuery();
        return RETURN_CODES.RETURN_Sucess;
    }

    public void OnSubmitRequestForm(View v) {
        String sTitle = mTitle.getText().toString();
        String sDescrip = mDescription.getText().toString();
        if(sTitle.isEmpty() || sDescrip.isEmpty()) {
            Toast.makeText(this, "Fill both Tile and Description", Toast.LENGTH_LONG);
            return;
        }

        mSrModal = null;
        TriggerDatabaseQuery();
    }

    @Override
    public void OnFBQueryComplete() {
        if(mAddDataAsyncTask.IsTaskSuccessfull()) {
            Intent retIntent = new Intent();
            retIntent.putExtra(Constants.EXTRAS_ACTIVITY_RESULT_MODAL, mSrModal);
            setResult(RESULT_OK, retIntent);
            finish();
            return;
        }
        Toast.makeText(this, "Failed to Add Service Request", Toast.LENGTH_LONG);
        setResult(RESULT_OK);
        finish();
    }
}

