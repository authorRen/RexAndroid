package com.rex.rexandroid.common;

import com.rex.rexandroid.component.RexApp;

/**
 * @author Ren ZeQiang
 * @since 2018/10/30.
 */
public class AppConfig {

    public AppConfig() {
    }

    public static final String OK_HTTP_CACHE_DIR = RexApp.getAppContext().getCacheDir() + "/okhttp";
    public static final Long OK_HTTP_CACHE_SIZE = 10 * 1024 * 1024L;
}
