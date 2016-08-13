package com.daffodilsw.servicestemplate;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An implementation of the web services instance creator.
 */
public final class ServiceManager {

    public static final String BASE_URL = "http://flagshippro.com/temporary/beeah";
    // the services endpoint, once the production and live server are given
    // then need to drive it through flavour.
    public static final String SERVICE_ENDPOINT = BASE_URL + "/apiv1/";
    public static final String IMAGE_ENDPOINT = BASE_URL + "/assets/images/";
    public static final String HEADER_TOTAL_PAGES = "total_pages";
    public static final String HEADER_TOTAL_ITEMS = "total_items";


    private static ServiceManager sServiceManager;

    /**
     * Gets the instance of the web services implementation.
     *
     * @return the singleton instance.
     */
    public static ServiceManager get() {
        if (sServiceManager == null) {
            sServiceManager = new ServiceManager();
        }
        return sServiceManager;
    }

    /**
     * Creates the services for a given HTTP Url, useful when testing
     * through multiple endpoints and unit testing
     *
     * @param clazz the service class.
     * @param <T>   type of the service.
     * @return the created services implementation.
     */
    public <T> T createService(Class<T> clazz) {
        return createService(clazz, HttpUrl.parse(SERVICE_ENDPOINT));
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

    public <T> T createService(Class<T> clazz, Retrofit retrofit) {
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
