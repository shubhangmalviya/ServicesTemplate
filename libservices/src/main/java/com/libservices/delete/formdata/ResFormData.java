package com.libservices.delete.formdata;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ResFormData {

    @JsonField(name = "user_id")
    public String mUserId;

    @JsonField
    public String mLongitude;

}
