package com.libservices.post.raw;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RawDataService {

    String PATH_REGISTER = "/register";

    String USER_ID = "userId";
    String PATH_CHANGE_USER_SETTING = "/user/{" + USER_ID + "}/setting";

    @POST(PATH_REGISTER)
    Call<ResRawData> postRequest(@Body ReqRawData reqRawData);

    @POST(PATH_CHANGE_USER_SETTING)
    Call<List<ResRawData>> postUserSetting(@Path(USER_ID) String userID , @Body ReqRawData reqRawData);

}
