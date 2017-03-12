package com.libapi.request.providers;

import okhttp3.RequestBody;

public interface RequestBodyProvider {
    RequestBody provideRequestBody();
}
