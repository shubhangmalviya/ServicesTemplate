package com.daffodilsw.servicestemplate.post.raw;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ReqRawData {

    @JsonField(name = "user_id")
    public String mUserId;

    @JsonField
    public String mLongitude;

}
