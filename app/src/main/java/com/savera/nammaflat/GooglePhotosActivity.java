package com.savera.nammaflat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.savera.nammaflat.Adapters.PhotoAlbumsAdapter;
import com.savera.nammaflat.Requests.photos.PhotoAsyncListAlbums;
import com.savera.nammaflat.modal.Album;

import java.util.ArrayList;

public class GooglePhotosActivity extends GoogleAuthActivity {

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

        //String URL = "https://photoslibrary.googleapis.com/v1/albums?access_token=255720913047-mpngvtvqq7a9n4irlj8cq2ks08hhti6c.apps.googleusercontent.com&key=AIzaSyAa8yy0GdcGPHdtD083HiGGx_S0vMPScDM";
        //String URL = "https://photoslibrary.googleapis.com/v1/albums";

        mAsynTaskListAlbums = new PhotoAsyncListAlbums(this);
        mAsynTaskListAlbums.ExecutePhotosQuery();

        /*new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    String token = googleAuthToken;

                    Credentials credential = getUserCredentials(REQUIRED_SCOPES);
                    token = mCredential.getAccessToken();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return null;
            }
        }.execute();*/
        /*new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    AccessTokenFactory a = new AccessTokenFactory();
                    a.requestAccessToken(null);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return null;
            }
        }.execute();*/


        //PhotosLibraryClient
        /*RequestQueue req = Volley.newRequestQueue(this);
        StringRequest objectRequest = new StringRequest(
                Request.Method.GET, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("access_token", "255720913047-qpc4k66grnvtph5bjnmhhua42utbkqf4.apps.googleusercontent.com");
               // params.put("key=", googleAuthToken);
                params.put("Authorization", Constants.HEADER_AUTH_VAL_PRFX + googleAuthToken);
                return params;
            }


        };

        req.add(objectRequest);*/
        /*try {
            PhotosLibraryClient client =
                    PhotosLibraryClientFactory.createClient("file:///android_asset/credentials.json", REQUIRED_SCOPES);
            InternalPhotosLibraryClient.ListAlbumsPagedResponse var = client.listAlbums();
        }catch (Exception e) {

        }*/



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
        }
    }
}
