package com.libservices.post.urlformencoded;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

public interface FormUrlEncodedService {

    String PATH_POST = "post";

    @FormUrlEncoded
    @POST(PATH_POST)
    Call<ResFormData> makeFormEncodedRequest(@FieldMap Map<String, String> requestMap);

}
