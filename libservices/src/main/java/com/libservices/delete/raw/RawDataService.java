package com.libservices.delete.raw;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface RawDataService {

    String PATH_REGISTER = "/register";

    String USER_ID = "userId";
    String PATH_CHANGE_USER_SETTING = "/user/{" + USER_ID + "}/setting";

    @HTTP(method = "DELETE", path = PATH_REGISTER, hasBody = true)
    Call<ResRawData> postRequest(@Body ReqRawData reqRawData);

    @HTTP(method = "DELETE", path = PATH_CHANGE_USER_SETTING, hasBody = true)
    Call<List<ResRawData>> postUserSetting(@Path(USER_ID) String userID, @Body ReqRawData reqRawData);

}
