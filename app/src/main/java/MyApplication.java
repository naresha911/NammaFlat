import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyApplication extends Application {

    private static MyApplication mInstance;

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
