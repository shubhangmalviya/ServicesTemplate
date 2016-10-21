package com.libservices.delete.withoutbody;


import com.google.gson.annotations.SerializedName;

public class ResDeleteData {

    @SerializedName("auth_token")
    public String mAuthToken;

    @SerializedName("time_to_live")
    public String mTimeToLive;

}
