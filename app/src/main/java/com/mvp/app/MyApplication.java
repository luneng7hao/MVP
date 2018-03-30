package com.mvp.app;

import android.app.Application;
import android.util.Log;

import com.mvp.util.other.UtilUncaughtExceptionHandler;

/**
 * Created by Asion on 2018/1/9.
 */

public class MyApplication extends Application {

    private static final String LOGTAG = "catchExceptionApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOGTAG, "onCreate");
        Thread.setDefaultUncaughtExceptionHandler(UtilUncaughtExceptionHandler.getInstance(this));
    }
}
