package com.savera.nammaflat;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static GoogleAccountCredential mCredential;

    //Sheets names
    public static final String USERS_SHEET = "12lQ9nolDy3DgXwRtadRu_43_Qjbl9n77aE1xmJ6-GiQ";
    public static final String FLAT_REQUEST_SHEET = "14-OKPklVBEq7r7C7sj3nxd4K_ynYj-W1LpEiSy8GC7U";



    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static MyApplication getInstance()
    {
        return mInstance;
    }

    public boolean IsDataConnected() {
        ConnectivityManager connectMngr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectMngr != null) {
            activeNetworkInfo = connectMngr.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean IsWIFIConnected() {
        ConnectivityManager connectMngr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWorkInfo = null;
        if(connectMngr != null) {
            activeNetWorkInfo = connectMngr.getActiveNetworkInfo();
        }
        return activeNetWorkInfo !=null && activeNetWorkInfo.isConnected();
    }
}
