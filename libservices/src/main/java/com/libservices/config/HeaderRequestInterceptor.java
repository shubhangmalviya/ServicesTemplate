package com.libservices.config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Adds the API key and Auth token headers to the outgoing request.
 */
public class HeaderRequestInterceptor implements Interceptor {

    private final HeaderModifier mHeaderModifier;

    public HeaderRequestInterceptor(HeaderModifier headerModifier) {
        mHeaderModifier = headerModifier;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder requestBuilder = chain.request().newBuilder();

        mHeaderModifier.addApiKeyHeader(requestBuilder);
        mHeaderModifier.addAuthTokenHeaderIfRequired(requestBuilder);

        return chain.proceed(requestBuilder.build());
    }
}
