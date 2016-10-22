package com.libservices.core;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigurationManager {

    public Retrofit createRetrofit(String serviceEndpoint, HeaderRequestInterceptor interceptor) {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(serviceEndpoint);

        configureConverterFactory(builder);

        configureClient(builder, interceptor);

        return builder.build();
    }

    private void configureConverterFactory(Retrofit.Builder retrofit) {
        retrofit.addConverterFactory(GsonConverterFactory.create());
    }

    private void configureClient(Retrofit.Builder retrofit, HeaderRequestInterceptor interceptor) {

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.interceptors().add(interceptor);

        retrofit.client(okHttpClient);
    }
}
