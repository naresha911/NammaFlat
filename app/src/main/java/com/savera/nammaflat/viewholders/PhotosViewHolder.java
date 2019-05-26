package com.savera.nammaflat.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.savera.nammaflat.Adapters.PhotoAlbumsAdapter;
import com.savera.nammaflat.R;

public class PhotosViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView mNetworkImageView;
    public TextView         mAlbumTitle;
    public CardView        mCardView;
    public PhotoAlbumsAdapter mAlbumsAdapter;

    public PhotosViewHolder(PhotoAlbumsAdapter albumsAdapter, @NonNull View itemView) {
        super(itemView);

        mNetworkImageView = itemView.findViewById(R.id.ph_networkImageView);
        mAlbumTitle = itemView.findViewById(R.id.ph_album_title);
        //mCardView = itemView.findViewById(R.id.ph_album_cardview);
        mAlbumsAdapter = albumsAdapter;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlbumsAdapter.mRecyclerViewItemClickListener.OnItemClickListener(getAdapterPosition(), v);
            }
        });
    }
}
