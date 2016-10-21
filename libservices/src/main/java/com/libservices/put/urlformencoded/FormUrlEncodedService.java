package com.libservices.put.urlformencoded;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

import java.util.Map;

public interface FormUrlEncodedService {

    String PATH_POST = "post";

    @FormUrlEncoded
    @PUT(PATH_POST)
    Call<ResFormEncodedData> makeFormEncodedRequest(@FieldMap Map<String, String> requestMap);

}
