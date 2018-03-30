package com.mvp.model.http;

import android.content.Context;
import android.util.Log;

import com.mvp.app.HttpConstants;
import com.mvp.model.http.factory.StringConverterFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asion on 2017/12/13.
 * Retrofit+Rxjava工具类，用于配置、封装
 */

public class RetrofitUtil {
    private static Context mContext;
    private static Api mApiService;//提供各种具体的网络请求
    public static String uuid="";//header信息中的uuid
    public RetrofitUtil(){
        uuid= UUID.randomUUID()+"";
    }
    public static Api getApiService() {
        if (mApiService == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            //设置请求超时时长
            okHttpClientBuilder.connectTimeout(HttpConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            //启用Log日志
            okHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor());
//            //设置缓存方式、时长、地址
//            okHttpClientBuilder.addNetworkInterceptor(getCacheInterceptor());
//            okHttpClientBuilder.addInterceptor(getCacheInterceptor());
//            okHttpClientBuilder.cache(getCache());
            //设置https访问(验证证书)
//            okHttpClientBuilder.hostnameVerifier
//                    (org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            //设置统一的header
            okHttpClientBuilder.addInterceptor(getHeaderInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    //服务器地址
                    .baseUrl(HttpConstants.HOST_SITE_HTTPS)
                    //配置转化库，采用String
//                    .addConverterFactory(StringConverterFactory.create())
                    //配置转化库，采用Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(okHttpClientBuilder.build())
                            .build();

            //为了调用方便，把请求方法都写在ApiService接口里
            mApiService = retrofit.create(Api.class);
        }

        return mApiService;
    }
    //配置头信息
    public static Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
               if(HttpConstants.ISHEADER) {
                   builder.header("device_type", "0");
                   builder.header("Content-Type", "application/json");
                   builder.header("Accept", "application/json");
                   builder.header("uuid", uuid);
                   Log.i("header", uuid);
               }

                Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }
    //提供Log日志插值器
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
