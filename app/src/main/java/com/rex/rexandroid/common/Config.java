package com.rex.rexandroid.common;

import com.rex.rexandroid.BuildConfig;

/**
 * @author Ren ZeQiang
 * @since 2018/12/19.
 */
public class Config {




    public static String getApiUrl() {
        if (BuildConfig.DEBUG) {
            return "";
        } else {
            return "";
        }
    }
}
