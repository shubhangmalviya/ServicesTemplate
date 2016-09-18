package com.libapi;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An implementation of the web services instance creator.
 */
public final class ServiceManager {

    /**
     * Creates the services for a given HTTP Url, useful when testing
     * through multiple endpoints and unit testing
     *
     * @param <T>   type of the service.
     * @param clazz the service class.
     * @param serviceEndpoint the service endpoint.
     * @return the created services implementation.
     */
    public <T> T createService(Class<T> clazz, String serviceEndpoint) {
        return createService(clazz, HttpUrl.parse(serviceEndpoint));
    }

    /**
     * Creates the services for a given HTTP Url, useful when testing
     * through multiple endpoints and unit testing
     *
     * @param clazz   the service class.
     * @param httpUrl the endpoint
     * @param <T>     type of the service.
     * @return the created services implementation.
     */
    public <T> T createService(Class<T> clazz, HttpUrl httpUrl) {
        Retrofit retrofit = getRetrofit(httpUrl);
        return retrofit.create(clazz);
    }

    private Retrofit getRetrofit(HttpUrl httpUrl) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(createClient())
                .addConverterFactory(getConverter())
                .build();
    }

    public Retrofit getPlainRetrofit(HttpUrl httpUrl) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(getConverter())
                .build();
    }

    private Converter.Factory getConverter() {
        return GsonConverterFactory.create();
    }


    private OkHttpClient createClient() {
        return new OkHttpClient.Builder().build();
    }

}
