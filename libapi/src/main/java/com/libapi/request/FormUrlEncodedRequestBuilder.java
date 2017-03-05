package com.libapi.request;

import okhttp3.FormBody;

/**
 * This serializes the Requests of the type x-www-form-urlencoded.
 */
public class FormUrlEncodedRequestBuilder {
    private final FormBody.Builder mBuilder;

    public FormUrlEncodedRequestBuilder() {
        mBuilder = new FormBody.Builder();
    }

    /**
     * Adds the key and value in the request.
     * <p>
     * Parameter names and values are URL encoded by default. Use {@link #addAlreadyEncoded }
     * to change this behavior.
     * </p>
     * <p>Calling with {@code ("key", "val")} yields Body as.
     * {@code key=val}.</p>
     * @param key the key.
     * @param value  the value.
     * @return the builder object.
     */
    public FormUrlEncodedRequestBuilder add(String key, String value) {
        mBuilder.add(key, value);
        return this;
    }

    /**
     * Adds if the parameter key and value are already URL encoded.
     * <p>Calling with value {@code key = key and Value = "foo+bar"} yields Body as {@code foo=foo+bar}.</p>
     * @param key the key.
     * @param value the value.
     * @return the builder object.
     *
     */
    public FormUrlEncodedRequestBuilder addAlreadyEncoded(String key, String value) {
        mBuilder.addEncoded(key, value);
        return this;
    }


    public FormBody build() {
        return mBuilder.build();
    }
}
