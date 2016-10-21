package com.libservices.post.urlformencoded;

import java.util.HashMap;
import java.util.Map;

public class ReqFormEncodedData {

    private final Map<String, String> mRequestBodyMap;

    private ReqFormEncodedData(Builder builder) {
        mRequestBodyMap = builder.mRequestBodyMap;
    }

    public Map<String, String> getRequestBodyMap() {
        return mRequestBodyMap;
    }

    public static final class Builder {

        private static final String FIELD_USER_ID = "user_id";
        private static final String FIELD_LONGITUDE = "longitude";

        private final Map<String, String> mRequestBodyMap;

        public Builder() {
            this.mRequestBodyMap = new HashMap<>();
        }


        public Builder withUserId(String userId) {
            mRequestBodyMap.put(FIELD_USER_ID, userId);
            return this;
        }

        public Builder withLongitude(String latitude) {
            mRequestBodyMap.put(FIELD_LONGITUDE, latitude);
            return this;
        }

        public ReqFormEncodedData build() {
            return new ReqFormEncodedData(this);
        }
    }
}
