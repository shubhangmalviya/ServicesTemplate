package com.libapi.request;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParam {


    public static class Builder {
        private final Map<String, String> mNonRepeatableNameValues;
        private final Map<String, List<String>> mRepeatableNameValues;

        public Builder() {
            mNonRepeatableNameValues = new HashMap<>();
            mRepeatableNameValues = new HashMap<>();
        }

        public Builder addNonRepeatableQueryParam(String name, String value) {

            return this;
        }

        public Builder addRepeatableQueryParam(String name, String value) {

            return this;
        }

        public void build() {

        }
    }
}
