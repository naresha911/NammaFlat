package com.savera.nammaflat.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.savera.nammaflat.Adapters.EventsAdapter;
import com.savera.nammaflat.R;

public class EventsViewHolder extends RecyclerView.ViewHolder {

    // public ImageView imageView;
    public TextView textViewEVTitle;
    public TextView textViewEVDescription;
    public TextView textViewEVStatus;
    public TextView textViewEVDate;
    public TextView textViewEVUser;
    private EventsAdapter mAdapter;

    public EventsViewHolder(EventsAdapter adapter, @NonNull View itemView) {
        super(itemView);

        mAdapter = adapter;
        //imageView = itemView.findViewById(R.id.imageViewReqImage);
        textViewEVTitle = itemView.findViewById(R.id.ev_title);
        textViewEVDescription = itemView.findViewById(R.id.ev_description);
        textViewEVStatus = itemView.findViewById(R.id.ev_status);
        textViewEVDate = itemView.findViewById(R.id.ev_date);
        textViewEVUser = itemView.findViewById(R.id.ev_userid);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.mRecyclerViewItemClickListener.OnItemClickListener(getAdapterPosition(), v);
            }
        });
    }
}
