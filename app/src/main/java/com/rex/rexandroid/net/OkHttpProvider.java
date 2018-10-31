package com.rex.rexandroid.net;

import com.rex.rexandroid.common.AppConfig;
import com.rex.rexandroid.util.FileUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * OkHttpClient 提供者.
 *
 * @author Ren ZeQiang
 * @since 2018/10/30.
 */
public class OkHttpProvider {
    public static final long DEFAULT_CONNECT_TIMEOUT = 15;
    public static final long DEFAULT_WRITE_TIMEOUT = 20;
    public static final long DEFAULT_READ_TIMEOUT = 20;

    private OkHttpClient mOkHttpClient;

    public static OkHttpProvider newInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final OkHttpProvider INSTANCE = new OkHttpProvider();
    }

    private OkHttpProvider() {
        initOkHttp();
    }

    private void initOkHttp() {
        new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(FileUtil.createDir(AppConfig.OK_HTTP_CACHE_DIR), AppConfig.OK_HTTP_CACHE_SIZE));
    }
}
