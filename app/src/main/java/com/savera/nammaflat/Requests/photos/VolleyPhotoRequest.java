package com.savera.nammaflat.Requests.photos;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.savera.nammaflat.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.savera.nammaflat.MyApplication.googleAuthToken;

public class VolleyPhotoRequest extends JsonObjectRequest {
    public VolleyPhotoRequest(int method, String url, @Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

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
}
