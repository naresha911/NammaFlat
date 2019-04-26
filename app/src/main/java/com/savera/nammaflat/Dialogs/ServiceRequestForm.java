package com.savera.nammaflat.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.savera.nammaflat.R;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.util.HashMap;
import java.util.Map;

public class ServiceRequestForm extends AppCompatDialogFragment {
    ServiceRequestModal mSRModel;
    EditText mTitle;
    EditText mDescription;
    Button   mSubmit;

    private static final String TAG = "RequestForm";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_service_request_form, null))
                .setTitle("ADD Service Request")
                // Add action buttons
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}

/*@Override

    public void OnSubmit(View view) {
        String sTitle = mTitle.getText().toString();
        String sDescrip = mDescription.getText().toString();

        if(sTitle.isEmpty() || sDescrip.isEmpty()) {
            Toast.makeText(getActivity(), "Complete all Fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> request = new HashMap<>();
        request.put("title", sTitle);
        request.put("description", sDescrip);

        db.collection("requests").document("First").set(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }*/