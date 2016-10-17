package com.libservices.config;

import com.libpersistance.Session;
import com.libpersistance.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Adds the API key and Auth token headers to the outgoing request.
 */
public class HeaderRequestInterceptor implements Interceptor {

    private static final String HEADER_API_KEY = "api_key";
    private static final String HEADER_AUTH_TOKEN = "auth_token";
    private static final String HEADER_API_KEY_VALUE = "cf40165bf171df14935fd2723fa49d45ea310c24dc8542b3c00ec9147017e2f1";
    private final SessionManager mSessionManager;

    public HeaderRequestInterceptor(SessionManager sessionManager) {
        mSessionManager = sessionManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder requestBuilder = chain.request().newBuilder();

        addApiKeyHeader(requestBuilder);
        addAuthTokenHeaderIfRequired(requestBuilder);

        return chain.proceed(requestBuilder.build());
    }

    private void addApiKeyHeader(Request.Builder requestBuilder) {
        requestBuilder.addHeader(HEADER_AUTH_TOKEN, HEADER_API_KEY_VALUE);
    }

    private void addAuthTokenHeaderIfRequired(Request.Builder requestBuilder) {
        Session session = mSessionManager.readSession();
        if (session.isValid()) {
            requestBuilder.addHeader(HEADER_API_KEY, session.getAuthToken());
        }
    }
}
