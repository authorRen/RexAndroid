package com.rex.rexandroid.component;

import android.app.Application;
import android.content.Context;

/**
 * @author Ren ZeQiang
 * @since 2018/10/25.
 */
public class RexApp extends Application {
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getAppContext();

    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
