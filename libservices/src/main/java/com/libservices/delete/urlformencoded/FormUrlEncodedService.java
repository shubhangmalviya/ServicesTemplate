package com.libservices.delete.urlformencoded;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;

import java.util.Map;

public interface FormUrlEncodedService {

    String PATH_POST = "post";

    @FormUrlEncoded
//    @HTTP
    Call<ResDeleteFormEncodedData> makeFormEncodedRequest(@FieldMap Map<String, String> requestMap);

}
