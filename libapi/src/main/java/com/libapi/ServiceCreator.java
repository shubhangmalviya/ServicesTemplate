package com.libapi;

import retrofit2.Retrofit;

/**
 * An implementation of the web services instance creator.
 */
public class ServiceCreator {

    private final Retrofit mRetrofit;

    /**
     * Creates an instance.
     * @param retrofit  the retrofit object.
     */
    public ServiceCreator(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    /**
     * Creates the services for a given HTTP Url, useful when testing
     * through multiple endpoints and unit testing
     *
     * @param <T>   type of the service.
     * @param clazz the service class.
     * @return the created services implementation.
     */
    public <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
