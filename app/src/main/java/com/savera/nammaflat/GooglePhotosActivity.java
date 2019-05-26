package com.savera.nammaflat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.savera.nammaflat.Adapters.PhotoAlbumsAdapter;
import com.savera.nammaflat.Adapters.RecyclerViewItemClickListener;
import com.savera.nammaflat.Requests.photos.PhotoAsyncListAlbums;
import com.savera.nammaflat.modal.photos.Album;

import java.util.ArrayList;

public class GooglePhotosActivity extends GoogleAuthActivity implements RecyclerViewItemClickListener {

    private RecyclerView mRecyclerView;
    private PhotoAlbumsAdapter mPhotoAlbumsAdapter;
    private PhotoAsyncListAlbums mAsynTaskListAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_photos);

        mRecyclerView = findViewById(R.id.ph_albumsrecyclerview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TriggerDatabaseQuery();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {

        mAsynTaskListAlbums = new PhotoAsyncListAlbums(this);
        mAsynTaskListAlbums.ExecutePhotosQuery();

        return null;
    }

    @Override
    public void OnFBQueryComplete() {
        ArrayList<Album> resultObjs = mAsynTaskListAlbums.mAlbums;

        if(!resultObjs.isEmpty()) {
            mPhotoAlbumsAdapter = new PhotoAlbumsAdapter(this, mAsynTaskListAlbums.mAlbums);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mPhotoAlbumsAdapter);
            mPhotoAlbumsAdapter.RegisterItemClickListner(this);
        }
    }

    @Override
    public boolean OnItemClickListener(int position, View v) {
        ArrayList<Album> resultAlbums = mAsynTaskListAlbums.GetAlbums();
        if(resultAlbums.isEmpty())
            return false;

        Intent launchSRFormActivity = new Intent(this, GalleryActivity.class);
        launchSRFormActivity.putExtra("model_data", resultAlbums.get(position));
        startActivity(launchSRFormActivity);
        return true;
    }
}
