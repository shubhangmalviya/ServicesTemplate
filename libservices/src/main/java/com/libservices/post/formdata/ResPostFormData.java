package com.libservices.post.formdata;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ResPostFormData {

    @JsonField(name = "user_id")
    public String mUserId;

    @JsonField
    public String mLongitude;

}
