package com.savera.nammaflat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savera.nammaflat.R;
import com.savera.nammaflat.modal.FBModalEntityList;
import com.savera.nammaflat.modal.ServiceRequestModal;
import com.savera.nammaflat.viewholders.ServiceReqViewHolder;

public class ServiceReqAdapter extends RecyclerView.Adapter<ServiceReqViewHolder>{

    private Context mtx;
    private FBModalEntityList<ServiceRequestModal> mListServiceReqs;
    public RecyclerViewItemClickListener mRecyclerViewItemClickListener;

    public ServiceReqAdapter(Context ctx, FBModalEntityList<ServiceRequestModal> listReqs) {
        mtx = ctx;
        mListServiceReqs = listReqs;
    }

    @NonNull
    @Override
    public ServiceReqViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.service_request_single_card2, viewGroup, false);
        return new ServiceReqViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceReqViewHolder serviceReqViewHolder, int i) {
        ServiceRequestModal serviceReq = mListServiceReqs.GetAt(i);

        serviceReqViewHolder.textViewReqTitle.setText(serviceReq.getTitle());
        serviceReqViewHolder.textViewReqDescription.setText(serviceReq.getDescription());
        serviceReqViewHolder.textViewReqStatus.setText("Temp Text");
        serviceReqViewHolder.textViewReqCategory.setText("CARPENTRY");

        //serviceReqViewHolder.textViewReqCategory.setText(serviceReq.getReqCategory());
    }

    @Override
    public int getItemCount() {
        return mListServiceReqs.GetCount();
    }

    public void RegisterItemClickListner(RecyclerViewItemClickListener srItemclickListener) {
        mRecyclerViewItemClickListener = srItemclickListener;
    }
}
