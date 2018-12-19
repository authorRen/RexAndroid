package com.rex.rexandroid.net.Retrofit;

import com.rex.rexandroid.common.Config;
import com.rex.rexandroid.net.OkHttpProvider;
import com.rex.rexandroid.util.JsonUtil;
import com.rex.rexandroid.util.StringUtil;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 接口服务工厂类
 *
 * @author Ren ZeQiang
 * @since 2018/12/19.
 */
public class ApiServiceFactory {

    //缓存Retrofit
    private final HashMap<String, Retrofit> mRetrofits;

    private ApiServiceFactory() {
        mRetrofits = new HashMap<>();
    }

    private static class SingletonHolder {
        private static ApiServiceFactory INSTANCE = new ApiServiceFactory();
    }

    public <S> S createService(Class<S> serviceClass) {
        return getRetrofit().create(serviceClass);
    }

    public <S> S createService(final String baseUrl, Class<S> serviceClass) {
        return getRetrofit(baseUrl).create(serviceClass);
    }


    public Retrofit getRetrofit() {
        return getRetrofit(Config.getApiUrl());
    }

    public Retrofit getRetrofit(final String baseUrl) {
        if (StringUtil.isNullOrEmpty(baseUrl)) {
            throw new RuntimeException("Base URL required.");
        }
        if (mRetrofits.containsKey(baseUrl)) {
            return mRetrofits.get(baseUrl);
        } else {
            Retrofit retrofit = buildRetrofit(baseUrl);
            mRetrofits.put(baseUrl, retrofit);
            return retrofit;
        }
    }

    private Retrofit buildRetrofit(final String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpProvider.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(JsonUtil.getGson()))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



}
