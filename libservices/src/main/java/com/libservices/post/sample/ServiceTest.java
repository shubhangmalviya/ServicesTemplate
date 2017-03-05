package com.libservices.post.sample;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

public interface ServiceTest {

    /**
     * This is valid for the request which has a body.
     * @param body
     * @return
     */
    Call<ResPostFormData1> makeRequest(@Body RequestBody body);

}
