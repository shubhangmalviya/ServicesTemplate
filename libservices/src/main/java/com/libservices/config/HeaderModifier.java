package com.libservices.config;

import com.libpersistance.Session;
import com.libpersistance.SessionManager;

import okhttp3.Request;

public class HeaderModifier {

    public static final String HEADER_API_KEY = "api_key";
    public static final String HEADER_AUTH_TOKEN = "auth_token";
    public static final String HEADER_API_KEY_VALUE = "cf40165bf171df14935fd2723fa49d45ea310c24dc8542b3c00ec9147017e2f1";
    private final SessionManager mSessionManager;

    public HeaderModifier(SessionManager sessionManager) {
        mSessionManager = sessionManager;
    }

    public void addApiKeyHeader(Request.Builder requestBuilder) {
        requestBuilder.addHeader(HEADER_AUTH_TOKEN, HEADER_API_KEY_VALUE);
    }

    public void addAuthTokenHeaderIfRequired(Request.Builder requestBuilder) {
        Session session = mSessionManager.readSession();
        if (session.isValid()) {
            requestBuilder.addHeader(HEADER_API_KEY, session.getAuthToken());
        }
    }

}
