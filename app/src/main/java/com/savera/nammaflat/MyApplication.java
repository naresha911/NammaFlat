package com.savera.nammaflat;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static String mGoogleAccountName;
    public static String googleAuthToken;

    public MyApplication() {
        mGoogleAccountName = null;
    }

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
}
