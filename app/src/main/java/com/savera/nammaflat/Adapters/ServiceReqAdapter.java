package com.savera.nammaflat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.savera.nammaflat.R;
import com.savera.nammaflat.modal.ServiceRequestModal;
import com.savera.nammaflat.viewholders.ServiceReqViewHolder;

import java.util.List;


public class ServiceReqAdapter extends RecyclerView.Adapter<ServiceReqViewHolder>{

    Context mtx;
    List<ServiceRequestModal> mListServiceReqs;
    public ServiceReqAdapter(Context ctx, List<ServiceRequestModal> listReqs) {
        mtx = ctx;
        mListServiceReqs = listReqs;
    }

    @NonNull
    @Override
    public ServiceReqViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.service_request_single_card, viewGroup, false);
        return new ServiceReqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceReqViewHolder serviceReqViewHolder, int i) {
        ServiceRequestModal serviceReq = mListServiceReqs.get(i);

        serviceReqViewHolder.textViewReqTitle.setText(serviceReq.getSzReqTitle());
        serviceReqViewHolder.textViewReqStatus.setText(serviceReq.getSzReqStatus());
        serviceReqViewHolder.textViewReqCategory.setText("1");
        //serviceReqViewHolder.textViewReqCategory.setText(serviceReq.getReqCategory());
    }

    @Override
    public int getItemCount() {
        return mListServiceReqs.size();
    }
}
