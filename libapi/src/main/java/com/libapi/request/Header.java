package com.libapi.request;

import java.util.HashMap;
import java.util.Map;

public class Header {

    private final Map<String, String> mHeaders;

    public Header(Builder builder) {
        mHeaders = builder.mHeaders;
    }

    public Map<String, String> getHeader() {
        return mHeaders;
    }

    public static class Builder {

        private final Map<String, String> mHeaders;

        public Builder() {
            mHeaders = new HashMap<>();
        }

        public Builder addKeyValue(String key, String value) {
            mHeaders.put(key, value);
            return this;
        }

        public Header build() {
            return new Header(this);
        }
    }
}
