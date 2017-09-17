package com.libservices.post.formdata;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface PostFormDataService {

    String PATH = "/rbwq3yrb";

    @Multipart
    @POST(value = PATH)
    Call<ResPostFormData> makeFormDataRequest(@Body MultipartBody body);


}
