package com.libservices.raw;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RawDataService {

    String PATH_REGISTER = "/register";

    @POST(PATH_REGISTER)
    Call<ResRawData> postRequest(@Body ReqRawData reqRawData);

}
