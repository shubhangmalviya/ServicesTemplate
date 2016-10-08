package com.libservices.raw;


import com.google.gson.annotations.SerializedName;

public class ResRawData {

    @SerializedName("auth_token")
    public String mAuthToken;

    @SerializedName("time_to_live")
    public String mTimeToLive;

}
