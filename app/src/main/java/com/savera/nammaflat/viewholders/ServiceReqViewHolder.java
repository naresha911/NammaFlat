package com.savera.nammaflat.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.savera.nammaflat.R;

public class ServiceReqViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textViewReqTitle;
    public TextView textViewReqStatus;
    public TextView textViewReqCategory;

    public ServiceReqViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageViewReqImage);
        textViewReqTitle = itemView.findViewById(R.id.textViewTitle);
        textViewReqStatus = itemView.findViewById(R.id.textViewReqStatus);
        textViewReqCategory = itemView.findViewById(R.id.textViewReqCategory);
    }
}
