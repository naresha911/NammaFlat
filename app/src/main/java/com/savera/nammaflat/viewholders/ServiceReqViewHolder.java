package com.savera.nammaflat.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.savera.nammaflat.Adapters.ServiceReqAdapter;
import com.savera.nammaflat.R;

public class ServiceReqViewHolder extends RecyclerView.ViewHolder {
   // public ImageView imageView;
    public TextView textViewReqTitle;
    public TextView textViewReqDescription;
    public TextView textViewReqStatus;
    public TextView textViewReqCategory;
    private ServiceReqAdapter mAdapter;

    public ServiceReqViewHolder(ServiceReqAdapter adapter, @NonNull View itemView) {
        super(itemView);

        mAdapter = adapter;
        //imageView = itemView.findViewById(R.id.imageViewReqImage);
        textViewReqTitle = itemView.findViewById(R.id.sr_title);
        textViewReqDescription = itemView.findViewById(R.id.sr_description);
        textViewReqStatus = itemView.findViewById(R.id.sr_status);
        textViewReqCategory = itemView.findViewById(R.id.sr_category);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.mRecyclerViewItemClickListener.OnItemClickListener(getAdapterPosition(), v);
            }
        });
    }
}
