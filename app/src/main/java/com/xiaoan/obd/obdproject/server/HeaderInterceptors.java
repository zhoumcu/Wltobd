package com.xiaoan.obd.obdproject.server;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptors implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("apikey", "a1025f23ac8737d78b0370a8ffc86a67");
        builder.addHeader("Cache-Control","public, only-if-cached, max-stale=" + maxStale);
        builder.addHeader("Accept-Encoding","gzip, deflate");
        builder.addHeader("Content-Type","application/json;charset=utf-8");
        builder.addHeader("Accept","application/json");
        return chain.proceed(builder.build());
    }
}