package com.libservices.put.raw;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface RawDataService {

    String PATH_REGISTER = "/register";

    String USER_ID = "userId";
    String PATH_CHANGE_USER_SETTING = "/user/{" + USER_ID + "}/setting";

    @PUT(PATH_REGISTER)
    Call<ResRawData> postRequest(@Body ReqRawData reqRawData);

    @PUT(PATH_CHANGE_USER_SETTING)
    Call<List<ResRawData>> postUserSetting(@Path(USER_ID) String userID, @Body ReqRawData reqRawData);

}
