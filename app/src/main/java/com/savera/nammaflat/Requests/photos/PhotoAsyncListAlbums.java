package com.savera.nammaflat.Requests.photos;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.savera.nammaflat.Constants;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.modal.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.savera.nammaflat.Constants.ALBUMS;

public class PhotoAsyncListAlbums extends PhotoAsyncTask {
    public ArrayList<Album> mAlbums;
    public String mNextPageToken;

    public PhotoAsyncListAlbums(GoogleAuthActivity authActivity) {
        super(authActivity);
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            mAlbums = new ArrayList<>();
            // If response doesnt have nextLink, then set the variable to null so that
            // we can detect that there are no more pages to come
            if(response.has(Constants.PHOTOS_ALBUMS_NEXT_PAGE_TOKEN)) {
                mNextPageToken = response.getString(Constants.PHOTOS_ALBUMS_NEXT_PAGE_TOKEN);
            } else {
                mNextPageToken = null;
            }
            // Extract the JSONArray containing objects representing each image.
            JSONArray itemsArray = response.getJSONArray(ALBUMS);
            Log.d(mAuthActivity.get().TAG,"Received " + itemsArray.length() + " photos");
            String mimeType;

            // Iterate the list of images, extract information and build the photo list
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jObj = itemsArray.getJSONObject(i);

                //Result will have images and videos. Skip if a video is encountered
                Album al = new Album();
                al.id = jObj.getString(Constants.PHOTOS_ALBUMS_ID);
                al.title = jObj.getString(Constants.PHOTOS_ALBUMS_TITLE);
                al.productUrl = jObj.getString(Constants.PHOTOS_ALBUMS_PRODUCTURL);
                al.mediaItemsCount = Long.valueOf(jObj.getString(Constants.PHOTOS_ALBUMS_MEDIAITEMSCOUNT));
                al.coverPhotoBaseUrl = jObj.getString(Constants.PHOTOS_ALBUMS_COVERPHOTOBASEURL);
                al.coverPhotoMediaItemId = jObj.getString(Constants.PHOTOS_ALBUMS_COVERPHOTOMEDIAITEMID);

                mAlbums.add(al);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onResponse(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }

    @Override
    protected void RunPhotosQuery() throws IOException {

        VolleyPhotoRequest objectRequest = new VolleyPhotoRequest(
                VolleyPhotoRequest.Method.GET, Constants.PHOTOS_URL,null,this, this);

        VolleyRequestQueue.getInstance(mAuthActivity.get()).addToRequestQueue(objectRequest);
    }
}
