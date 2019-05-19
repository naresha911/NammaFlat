package com.savera.nammaflat.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.Requests.photos.VolleyRequestQueue;
import com.savera.nammaflat.modal.Album;
import com.savera.nammaflat.viewholders.PhotosViewHolder;
import com.savera.nammaflat.R;

import java.util.ArrayList;

public class PhotoAlbumsAdapter extends RecyclerView.Adapter<PhotosViewHolder> {
    private ArrayList<Album> mAlbums;
    private GoogleAuthActivity mActivity;
    private RecyclerViewItemClickListener mRecyclerViewItemClickListener;


    public PhotoAlbumsAdapter(GoogleAuthActivity activity, ArrayList<Album> albumArrayList) {
        mActivity = activity;
        mAlbums = albumArrayList;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mActivity.getBaseContext());
        View view = inflater.inflate(R.layout.photo_single_item, viewGroup, false);
        return new PhotosViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder photosViewHolder, int i) {
        Album album = mAlbums.get(i);

        String thumbNail = album.coverPhotoBaseUrl/* + "=w" + width + "-h" + height*/;
        photosViewHolder.mNetworkImageView.setImageUrl(thumbNail, VolleyRequestQueue.getInstance(mActivity).getImageLoader());
        photosViewHolder.mAlbumTitle.setText(album.title);

    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public void RegisterItemClickListner(RecyclerViewItemClickListener albumItemclickListener) {
        mRecyclerViewItemClickListener = albumItemclickListener;
    }
}
