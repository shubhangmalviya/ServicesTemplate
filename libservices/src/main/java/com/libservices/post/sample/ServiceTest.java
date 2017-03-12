package com.libservices.post.sample;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ServiceTest {

    /**
     * This is valid for the request which has a body.
     * @param body
     * @return
     */
    @Headers({ // Static Headers
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("/relative/path/{id}")
    Call<ResPostFormData1> makeRequest(@Path("id") String id,@Body RequestBody body, @QueryMap Map<String, String> param, @HeaderMap Map<String, String> headerMap);

}
