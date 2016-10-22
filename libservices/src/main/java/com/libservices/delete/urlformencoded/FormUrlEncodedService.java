package com.libservices.delete.urlformencoded;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;

public interface FormUrlEncodedService {

    String PATH_POST = "post";

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = PATH_POST, hasBody = true)
    Call<ResDeleteFormEncodedData> makeFormEncodedRequest(@FieldMap Map<String, String> requestMap);

}
