package com.libservices.post.sample;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

public interface ServiceTest {

    Call<ResPostFormData1> makeFormDataRequest(@Body RequestBody body);



}
