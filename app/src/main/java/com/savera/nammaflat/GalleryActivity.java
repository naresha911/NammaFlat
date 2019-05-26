package com.savera.nammaflat;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.savera.nammaflat.Adapters.GalleryAdapter;
import com.savera.nammaflat.Requests.photos.PhotoAsynclistMediaItemsInAlbum;
import com.savera.nammaflat.modal.photos.MediaItemData;

import java.util.ArrayList;

public class GalleryActivity extends GoogleAuthActivity {

    private ArrayList<MediaItemData> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    PhotoAsynclistMediaItemsInAlbum mMediaItemsInAlbumReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = (RecyclerView) findViewById(R.id.gallery_recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /* recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        fetchImages();
    }

    @Override
    protected RETURN_CODES ExecuteQuery() {
        mMediaItemsInAlbumReq = new PhotoAsynclistMediaItemsInAlbum(this, "AEX4GpGm5DseWMN0ENHcktBQw5w_Oi2ipKrI-2JsCyoOiuiqRLcSz_4dvnaHWB02k9JCTjYYnvf1");
        mMediaItemsInAlbumReq.ExecutePhotosQuery();
        return null;
    }


    private void fetchImages() {

        pDialog.setMessage("Downloading Photos...");
        pDialog.show();

        TriggerDatabaseQuery();
    }


    @Override
    public void OnFBQueryComplete() {

    }
}
