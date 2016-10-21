package com.libservices.get;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

// Header map can be extra.
interface GetDataService {

    String PATH_USER = "/user";

    String USER_ID = "userId";
    String PATH_GET_USER_SETTING = "/user/{" + USER_ID + "}/setting";

    @GET(PATH_USER)
    Call<ResGetData> getUser();

    @GET(PATH_USER)
    Call<ResGetData> getUserQueryParam(@QueryMap Map<String, String> params);

    @GET(PATH_GET_USER_SETTING)
    Call<List<ResGetData>> postUserSetting(@Path(USER_ID) String userID);

}
