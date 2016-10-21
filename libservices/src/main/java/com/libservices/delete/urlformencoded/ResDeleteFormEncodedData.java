package com.libservices.delete.urlformencoded;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ResDeleteFormEncodedData {

    @JsonField(name = "user_id")
    public String mUserId;

    @JsonField
    public String mLongitude;

}
