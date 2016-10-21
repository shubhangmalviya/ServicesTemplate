package com.libservices.post.raw;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostRawDataService {

    String PATH_REGISTER = "/register";

    String USER_ID = "userId";
    String PATH_CHANGE_USER_SETTING = "/user/{" + USER_ID + "}/setting";

    @POST(PATH_REGISTER)
    Call<ResPostRawData> postRequest(@Body ReqPostRawData reqPostRawData);

    @POST(PATH_CHANGE_USER_SETTING)
    Call<List<ResPostRawData>> postUserSetting(@Path(USER_ID) String userID , @Body ReqPostRawData reqPostRawData);

}
