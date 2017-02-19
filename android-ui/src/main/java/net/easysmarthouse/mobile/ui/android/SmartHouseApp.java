package net.easysmarthouse.mobile.ui.android;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import net.easysmarthouse.mobile.ui.android.util.ResourceHelper;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class SmartHouseApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }

    public static Resources getContextResources(){
        return getContext().getResources();
    }

    public static String getContextResourceString(String resourceName){
        return getContext().getResources().getString(
                ResourceHelper.getId(resourceName, R.string.class));
    }

    public static Resources getContextSystemResources(){
        return getContextResources().getSystem();
    }


}
