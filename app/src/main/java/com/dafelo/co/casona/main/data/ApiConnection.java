package com.dafelo.co.casona.main.data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 22/10/16.
 */

public class ApiConnection {

    private static final String BASE_URL = "http://192.168.0.13:3020/api/";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    private static final String NO_AUTH_FLAG = "CasonaAppFlag";

    public Retrofit createClient() {

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                   Request request = originalRequest.newBuilder()
                            .addHeader("authorization", NO_AUTH_FLAG)
                            .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                            .method(originalRequest.method(), originalRequest.body())
                            .build();

                    return chain.proceed(request);
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }
}
