package com.savera.nammaflat.Requests.photos;

import android.util.Log;

import com.savera.nammaflat.Constants;
import com.savera.nammaflat.GoogleAuthActivity;
import com.savera.nammaflat.modal.photos.Album;
import com.savera.nammaflat.modal.photos.MediaItemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.savera.nammaflat.Constants.ALBUMS;
import static com.savera.nammaflat.Constants.MEDIA_ITEMS;

//https://developers.google.com/photos/library/guides/list
/*POST https://photoslibrary.googleapis.com/v1/mediaItems:search
        Content-type: application/json
        Authorization: Bearer OAUTH2_TOKEN
        {
        "pageSize":"100",
        "albumId": "ALBUM_ID"
        }*/

public class PhotoAsynclistMediaItemsInAlbum extends PhotoAsyncTask {
    private Album mAlbumSelected;
    private String mNextPageToken;
    public ArrayList<MediaItemData> mArrayMediaItems;

    public PhotoAsynclistMediaItemsInAlbum(GoogleAuthActivity authActivity, Album albumSelected) {
        super(authActivity);
        mAlbumSelected = albumSelected;
        mArrayMediaItems = null;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if(mArrayMediaItems == null)
                mArrayMediaItems = new ArrayList<>();
            // If response doesnt have nextLink, then set the variable to null so that
            // we can detect that there are no more pages to come
            if(response.has(Constants.PHOTOS_ALBUMS_NEXT_PAGE_TOKEN)) {
                mNextPageToken = response.getString(Constants.PHOTOS_ALBUMS_NEXT_PAGE_TOKEN);
            } else {
                mNextPageToken = null;
            }
            // Extract the JSONArray containing objects representing each image.
            JSONArray itemsArray = response.getJSONArray(MEDIA_ITEMS);
            Log.d(mAuthActivity.get().TAG,"Received " + itemsArray.length() + " photos");

            // Iterate the list of images, extract information and build the photo list
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jObj = itemsArray.getJSONObject(i);

                //Result will have images and videos. Skip if a video is encountered
                MediaItemData mediaItem = new MediaItemData();
                mediaItem.mediaItemId = jObj.getString(Constants.PHOTOS_ID);
                //mediaItem.description = jObj.getString(Constants.PHOTOS_DESCRIPTION);
                mediaItem.productUrl = jObj.getString(Constants.PHOTOS_PRODUCTURL);
                mediaItem.baseUrl = jObj.getString(Constants.PHOTOS_MEDIA_BASEURL);
                mediaItem.mimeType = jObj.getString(Constants.PHOTOS_MEDIA_MIMETYPE);
                mediaItem.filename = jObj.getString(Constants.PHOTOS_MEDIA_FILENAME);

                mArrayMediaItems.add(mediaItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onResponse(response);

    }

    @Override
    protected void RunPhotosQuery() throws IOException {
        VolleyPhotoRequest objectRequest = new VolleyPhotoRequest(
                VolleyPhotoRequest.Method.POST, Constants.PHOTOS_MEDIA_ITEMS_SEARCH_URL,null,this, this);

        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", String.valueOf(mAlbumSelected.mediaItemsCount));
        params.put("albumId", mAlbumSelected.id);

        objectRequest.AppendAdditionalHeaders(params);

        VolleyRequestQueue.getInstance(mAuthActivity.get()).addToRequestQueue(objectRequest);

    }
}
