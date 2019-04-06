package com.savera.nammaflat.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.R;

public class ServiceReqViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textViewReqTitle;
    public TextView textViewReqStatus;
    public TextView textViewReqCategory;
    private ServiceReqAdapter mAdapter;

    public ServiceReqViewHolder(ServiceReqAdapter adapter, @NonNull View itemView) {
        super(itemView);

        mAdapter = adapter;
        imageView = itemView.findViewById(R.id.imageViewReqImage);
        textViewReqTitle = itemView.findViewById(R.id.textViewTitle);
        textViewReqStatus = itemView.findViewById(R.id.textViewReqStatus);
        textViewReqCategory = itemView.findViewById(R.id.textViewReqCategory);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.mServiceRequestItemClickListener.OnItemClickListener(getAdapterPosition(), v);
            }
        });
    }
}
