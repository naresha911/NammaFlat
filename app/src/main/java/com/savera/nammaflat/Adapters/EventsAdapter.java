package com.savera.nammaflat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savera.nammaflat.R;
import com.savera.nammaflat.modal.EventModal;
import com.savera.nammaflat.modal.FBModalEntityList;
import com.savera.nammaflat.viewholders.EventsViewHolder;

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {
    private Context mtx;
    private FBModalEntityList<EventModal> mListEvents;
    public RecyclerViewItemClickListener mRecyclerViewItemClickListener;

    public EventsAdapter(Context ctx, FBModalEntityList<EventModal> listReqs) {
        mtx = ctx;
        mListEvents = listReqs;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.show_event_single_card, viewGroup, false);
        return new EventsViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder eventsViewHolder, int i) {
        EventModal serviceReq = mListEvents.GetAt(i);

        eventsViewHolder.textViewEVTitle.setText(serviceReq.getTitle());
        eventsViewHolder.textViewEVDescription.setText(serviceReq.getDescription());
        eventsViewHolder.textViewEVStatus.setText("Temp Text");
        eventsViewHolder.textViewEVUser.setText("CARPENTRY");

    }

    @Override
    public int getItemCount() {
        return mListEvents.GetCount();
    }

    public void RegisterItemClickListner(RecyclerViewItemClickListener srItemclickListener) {
        mRecyclerViewItemClickListener = srItemclickListener;
    }
}
