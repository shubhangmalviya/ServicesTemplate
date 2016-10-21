package com.libservices.get;


import com.google.gson.annotations.SerializedName;

public class ResGetData {

    @SerializedName("auth_token")
    public String mAuthToken;

    @SerializedName("time_to_live")
    public String mTimeToLive;

}
