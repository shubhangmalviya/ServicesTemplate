package com.libapi.request;


import java.util.HashMap;
import java.util.Map;

public class QueryParam {

    private final Map<String, String> mQueryParam;

    private QueryParam(DistinctParamBuilder distinctParamBuilder) {
        mQueryParam = distinctParamBuilder.mQueryParam;
    }

    public Map<String, String> getQueryParam() {
        return mQueryParam;
    }

    public static class DistinctParamBuilder {
        private final Map<String, String> mQueryParam;

        public DistinctParamBuilder() {
            mQueryParam = new HashMap<>();
        }

        public DistinctParamBuilder addQueryParam(String name, String value) {
            mQueryParam.put(name, value);
            return this;
        }

        public QueryParam build() {
            return new QueryParam(this);
        }
    }

    public static class UrlBuilder {

        private final String mRelativeUrl;


        public UrlBuilder(String relativeUrl) {
            mRelativeUrl = relativeUrl;
        }

        public UrlBuilder addQueryParam(String name, String value) {

            return this;
        }


    }
}
