package com.libservices.config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigurationManager {

    public Retrofit createRetrofit(String serviceEndpoint, RequestInterceptor interceptor) {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(serviceEndpoint);

        configureInterceptor(builder);

        configureConverterFactory(builder);

        configureClient(builder, interceptor);

        return builder.build();
    }

    private void configureInterceptor(Retrofit.Builder retrofit) {

    }

    private void configureConverterFactory(Retrofit.Builder retrofit) {
        retrofit.addConverterFactory(GsonConverterFactory.create());
    }

    private void configureClient(Retrofit.Builder retrofit, RequestInterceptor interceptor) {

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.interceptors().add(interceptor);

        retrofit.client(okHttpClient);
    }


}
