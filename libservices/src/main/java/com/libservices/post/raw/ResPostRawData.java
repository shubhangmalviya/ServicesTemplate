package com.libservices.post.raw;


import com.google.gson.annotations.SerializedName;

public class ResPostRawData {

    @SerializedName("auth_token")
    public String mAuthToken;

    @SerializedName("time_to_live")
    public String mTimeToLive;

}
