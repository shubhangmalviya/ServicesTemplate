package com.libservices.delete.withoutbody;


import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

// Header map can be extra.
interface DeleteDataService {

    String PATH_USER = "/user/";
    String USER_ID = "userId";
    String PATH_GET_USER_SETTING = "/user/{" + USER_ID +"}";

    @DELETE(PATH_USER)
    Call<ResDeleteData> getUserQueryParam(@QueryMap Map<String, String> params);

    @DELETE(PATH_GET_USER_SETTING)
    Call<List<ResDeleteData>> postUserSetting(@Path(USER_ID) String userID);

}
