package com.savera.nammaflat.Requests.photos;

import com.savera.nammaflat.Constants;
import com.savera.nammaflat.GoogleAuthActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//https://developers.google.com/photos/library/guides/list
/*POST https://photoslibrary.googleapis.com/v1/mediaItems:search
        Content-type: application/json
        Authorization: Bearer OAUTH2_TOKEN
        {
        "pageSize":"100",
        "albumId": "ALBUM_ID"
        }*/

public class PhotoAsynclistMediaItemsInAlbum extends PhotoAsyncTask {
    String m_strAlbumID;

    public PhotoAsynclistMediaItemsInAlbum(GoogleAuthActivity authActivity, String albumID) {
        super(authActivity);
        m_strAlbumID = albumID;
    }

    @Override
    public void onResponse(JSONObject response) {
        super.onResponse(response);

        try{
            JSONObject

        }catch (IOException e){

        }

    }

    @Override
    protected void RunPhotosQuery() throws IOException {
        VolleyPhotoRequest objectRequest = new VolleyPhotoRequest(
                VolleyPhotoRequest.Method.POST, Constants.PHOTOS_MEDIA_ITEMS_SEARCH_URL,null,this, this);

        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", "100");
        params.put("albumId", m_strAlbumID);

        objectRequest.AppendAdditionalHeaders(params);

        VolleyRequestQueue.getInstance(mAuthActivity.get()).addToRequestQueue(objectRequest);

    }
}
