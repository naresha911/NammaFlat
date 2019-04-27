package com.savera.nammaflat.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.savera.nammaflat.Constants;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.R;
import com.savera.nammaflat.Requests.GoogleAsyncTask;
import com.savera.nammaflat.Utils.SharedPrefrncsUtils;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.io.IOException;
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
        FirebaseAddServiceRequest addServiceRequest = new FirebaseAddServiceRequest(this);
        addServiceRequest.execute();
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

    private void OnFirebaseSuccessQuery() {
        Intent retIntent = new Intent();
        retIntent.putExtra(Constants.EXTRAS_SERVICE_REQUEST_MODAL, mSrModal);
        setResult(RESULT_OK, retIntent);
        finish();
    }

    private class FirebaseAddServiceRequest extends GoogleAsyncTask {

        public FirebaseAddServiceRequest(GoogleAuthActivity authActivity) {
            super(authActivity);
        }

        @Override
        protected void Run() throws IOException {
            mAuthActivity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int nReqType = mReqType.getSelectedItemPosition();
                    int nCategory = mCategory.getSelectedItemPosition();
                    String sTitle = mTitle.getText().toString();
                    String sDescrip = mDescription.getText().toString();
                    int nStatus = mStatus.getSelectedItemPosition();

                    if (sTitle.isEmpty() || sDescrip.isEmpty()) {
                        Toast.makeText(mAuthActivity.get(), "Complete all Fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String sUserID = SharedPrefrncsUtils.getDefaults(Constants.USERS_ID, mAuthActivity.get());

                    Map<String, Object> request = new HashMap<>();
                    request.put(Constants.SR_TYPE, Long.valueOf(nReqType));
                    request.put(Constants.SR_CATEGORY, Long.valueOf(nCategory));
                    request.put(Constants.SR_TITLE, sTitle);
                    request.put(Constants.SR_DESCRIPTION, sDescrip);
                    request.put(Constants.SR_STATUS, Long.valueOf(nStatus));
                    request.put(Constants.SR_USERID, sUserID);

                    mSrModal = new ServiceRequestModal();
                    mSrModal.FillData(request);

                    CollectionReference srColRef = db.collection(Constants.SR_COLLECTION);
                    srColRef.add(mSrModal).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult() != null) {
                                    OnFirebaseSuccessQuery();
                                }


                            } else {

                            }
                        }

                    });
                }
            });

        }
    }
}

