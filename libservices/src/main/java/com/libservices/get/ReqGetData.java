package com.libservices.get;

import java.util.HashMap;
import java.util.Map;

public class ReqGetData {

    private final Map<String, String> mQueryMap;

    private ReqGetData(ReqGetData.Builder builder) {
        mQueryMap = builder.mQueryMap;
    }

    public Map<String, String> getQueryMap() {
        return mQueryMap;
    }

    public static final class Builder {

        private static final String QUERY_USER_ID = "user_id";
        private static final String QUERY_LONGITUDE = "longitude";

        private final Map<String, String> mQueryMap;

        public Builder() {
            this.mQueryMap = new HashMap<>();
        }

        public Builder withUserId(String userId) {
            mQueryMap.put(QUERY_USER_ID, userId);
            return this;
        }

        public Builder withLongitude(String latitude) {
            mQueryMap.put(QUERY_LONGITUDE, latitude);
            return this;
        }

        public ReqGetData build() {
            return new ReqGetData(this);
        }
    }

}
