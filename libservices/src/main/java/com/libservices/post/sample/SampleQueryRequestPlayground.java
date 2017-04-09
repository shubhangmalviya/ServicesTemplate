package com.libservices.post.sample;

import com.libapi.ApiRequest;
import com.libapi.ApplicationErrorCodes;
import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import com.libservices.R;
import com.libservices.core.ErrorCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Development Strategy for handling Query Request.
 */
public class SampleQueryRequestPlayground {

    // ------------------------------------------------------------------------------------
    // case 1: single query param.
    // http://google.com/lookUp?search=a
    public static class ReqSingleQuery {
        private final String mSearchParam;

        public ReqSingleQuery(Builder builder) {
            mSearchParam = builder.mSearchParam;
        }

        public String getSearchParam() {
            return "";
        }

        public static class Builder {
            private String mSearchParam;

            public Builder setSearch(String search) {
                mSearchParam = search;
                return this;
            }

            public ReqSingleQuery build() {
                return new ReqSingleQuery(this);
            }
        }
    }

    public static class ResSingleQuery {

    }

    public interface FolderGroupName {

        // http://google.com/lookUp?search=a
        // named after last path param "make + {Last Path Segment} + Request"
        @GET("lookUp")
        Call<ResSingleQuery> makeLookUpRequest(@Query("search") String search);

        // 2nd step: start searching the ordering of the parameters.
        // 3rd step: Error Handling mechanisms in the API request class.
    }

    public static class SingleQueryAPIRequest extends ApiRequest<ReqSingleQuery, ResSingleQuery, FolderGroupName>{

        /**
         * Creates an instance of the api request.
         *
         * @param errorResponseTransformer transformer useful in case of resolving the application error.
         * @param serviceCreator           the configured service manager for creating the services instances.
         */
        public SingleQueryAPIRequest(ErrorResponseTransformer errorResponseTransformer, ServiceCreator serviceCreator) {
            super(errorResponseTransformer, serviceCreator);
        }

        @Override
        protected Class<FolderGroupName> getServiceClass() {
            return FolderGroupName.class;
        }

        @Override
        protected Call<ResSingleQuery> makeRequest(ReqSingleQuery reqSingleQuery, FolderGroupName folderGroupName) {
            return folderGroupName.makeLookUpRequest(reqSingleQuery.getSearchParam());
        }

        @Override
        protected ErrorLookupTable prepareErrorLookupMessages() {
            ErrorLookupTable errorLookupTable = new ErrorLookupTable();

            errorLookupTable.translate(ApplicationErrorCodes.INTERNET_ERROR, R.string.err_connectivity);
            errorLookupTable.translate(ErrorCodes.E_500, R.string.err_internal_server_error);

            return errorLookupTable;
        }
    }


    // ------------------------------------------------------------------------------------
    // case 2: n distinct query param.
    // http://google.com/register?username=a&password=b&email=c
    public static class ReqNDistinctQuery {

        private final Map<String, String> mQueryParam;

        public ReqNDistinctQuery(Builder builder) {
            mQueryParam = builder.mQueryParam;
        }

        // order the methods similar to the methods in the request interface parameters.
        public Map<String, String> getQueryParam() {
            return mQueryParam;
        }

        public static class Builder {

            private final String PARAM_USERNAME = "username";
            private final String PARAM_PASSWORD = "password";
            private final String PARAM_EMAIL = "email";
            private final Map<String, String> mQueryParam;

            public Builder() {
                mQueryParam = new HashMap<>();
            }

            public Builder setUserName(String userName) {
                mQueryParam.put(PARAM_USERNAME, userName);
                return this;
            }

            public Builder setPassword(String password) {
                mQueryParam.put(PARAM_PASSWORD, password);
                return this;
            }

            public Builder setEmail(String email) {
                mQueryParam.put(PARAM_EMAIL, email);
                return this;
            }

            public ReqNDistinctQuery build() {
                return new ReqNDistinctQuery(this);
            }
        }
    }

    public static class ResNDesitinctQuery {

    }

    public interface FolderGroupName2 {

        // named after last path param "make + {Last Path Segment} + Request"
        @GET("register")
        Call<ResNDesitinctQuery> makeRegisterRequest(@QueryMap Map<String, String> param);
    }


    public static class NDistinctQueryAPIRequest extends ApiRequest<ReqNDistinctQuery, ResNDesitinctQuery, FolderGroupName2>{

        /**
         * Creates an instance of the api request.
         *
         * @param errorResponseTransformer transformer useful in case of resolving the application error.
         * @param serviceCreator           the configured service manager for creating the services instances.
         */
        public NDistinctQueryAPIRequest(ErrorResponseTransformer errorResponseTransformer, ServiceCreator serviceCreator) {
            super(errorResponseTransformer, serviceCreator);
        }

        @Override
        protected Class<FolderGroupName2> getServiceClass() {
            return FolderGroupName2.class;
        }

        @Override
        protected Call<ResNDesitinctQuery> makeRequest(ReqNDistinctQuery reqSingleQuery, FolderGroupName2 folderGroupName) {
            return folderGroupName.makeRegisterRequest(reqSingleQuery.getQueryParam());
        }

        @Override
        protected ErrorLookupTable prepareErrorLookupMessages() {
            ErrorLookupTable errorLookupTable = new ErrorLookupTable();

            errorLookupTable.translate(ApplicationErrorCodes.INTERNET_ERROR, R.string.err_connectivity);
            errorLookupTable.translate(ErrorCodes.E_500, R.string.err_internal_server_error);

            return errorLookupTable;
        }
    }

    // ------------------------------------------------------------------------------------
    // case 3: repeatable query param.
    // http://google.com/find?username=a&username=b&username=c
    public static class ReqRepeatableQuery {

        private final List<String> mQueryParam;

        public ReqRepeatableQuery(Builder builder) {
            mQueryParam = builder.mParameter;
        }

        public List<String> getQueryParam() {
            return mQueryParam;
        }

        public static class Builder {

            private final List<String> mParameter;

            public Builder() {
                mParameter = new ArrayList<>();
            }

            public void addUsername(String userName) {
                mParameter.add(userName);
            }

            public ReqRepeatableQuery build() {
                return new ReqRepeatableQuery(this);
            }
        }
    }

    public static class ResRepeatableQuery {

    }

    public interface FolderGroupName3 {

        // named after last path param "make + {Last Path Segment} + Request"
        @GET("register")
        Call<ResRepeatableQuery> makeFindRequest(@Query("username") List<String> param);
    }


    public static class RepeatableQueryAPIRequest extends ApiRequest<ReqRepeatableQuery, ResRepeatableQuery, FolderGroupName3>{

        /**
         * Creates an instance of the api request.
         *
         * @param errorResponseTransformer transformer useful in case of resolving the application error.
         * @param serviceCreator           the configured service manager for creating the services instances.
         */
        public RepeatableQueryAPIRequest(ErrorResponseTransformer errorResponseTransformer, ServiceCreator serviceCreator) {
            super(errorResponseTransformer, serviceCreator);
        }

        @Override
        protected Class<FolderGroupName3> getServiceClass() {
            return FolderGroupName3.class;
        }

        @Override
        protected Call<ResRepeatableQuery> makeRequest(ReqRepeatableQuery reqSingleQuery, FolderGroupName3 folderGroupName) {
            return folderGroupName.makeFindRequest(reqSingleQuery.getQueryParam());
        }

        @Override
        protected ErrorLookupTable prepareErrorLookupMessages() {
            ErrorLookupTable errorLookupTable = new ErrorLookupTable();

            errorLookupTable.translate(ApplicationErrorCodes.INTERNET_ERROR, R.string.err_connectivity);
            errorLookupTable.translate(ErrorCodes.E_500, R.string.err_internal_server_error);

            return errorLookupTable;
        }
    }


    // ------------------------------------------------------------------------------------
    //case 4: Single Query param + n Repeatable Query Param.
    // http://google.com/lookUp?search=a&keyword=b&keyword=c
    public static class ReqSingleAndRepeatableQueryParam {

        private final String mSearchParam;
        private final List<String> mKeywordParam;

        public ReqSingleAndRepeatableQueryParam(Builder builder) {
            mSearchParam = builder.mSearchParam;
            mKeywordParam = builder.mKeywordParam;
        }

        public String getSearchParam() {
            return mSearchParam;
        }

        public List<String> getKeywordParam() {
            return mKeywordParam;
        }

        public static class Builder {

            private String mSearchParam;
            private final List<String> mKeywordParam;

            public Builder() {
                mKeywordParam = new ArrayList<>();
            }

            public Builder setSearch(String search) {
                mSearchParam = search;
                return this;
            }

            public Builder addKeyword(String keyword) {
                mKeywordParam.add(keyword);
                return this;
            }

            public ReqSingleAndRepeatableQueryParam build() {
                return new ReqSingleAndRepeatableQueryParam(this);
            }
        }
    }

    public static class ResSingleAndRepeatableParam {

    }

    public interface FolderGroupName4 {

        // named after last path param "make + {Last Path Segment} + Request"
        @GET("lookUp")
        Call<ResSingleAndRepeatableParam> makeLookUpRequest(@Query("search") String search, @Query("username") List<String> param);
    }

    public static class SingleAndRepeatableParamAPIRequest extends ApiRequest<ReqSingleAndRepeatableQueryParam, ResSingleAndRepeatableParam, FolderGroupName4>{

        /**
         * Creates an instance of the api request.
         *
         * @param errorResponseTransformer transformer useful in case of resolving the application error.
         * @param serviceCreator           the configured service manager for creating the services instances.
         */
        public SingleAndRepeatableParamAPIRequest(ErrorResponseTransformer errorResponseTransformer, ServiceCreator serviceCreator) {
            super(errorResponseTransformer, serviceCreator);
        }

        @Override
        protected Class<FolderGroupName4> getServiceClass() {
            return FolderGroupName4.class;
        }

        @Override
        protected Call<ResSingleAndRepeatableParam> makeRequest(ReqSingleAndRepeatableQueryParam reqSingleQuery, FolderGroupName4 folderGroupName) {
            return folderGroupName.makeLookUpRequest(reqSingleQuery.getSearchParam(), reqSingleQuery.getKeywordParam());
        }

        @Override
        protected ErrorLookupTable prepareErrorLookupMessages() {
            ErrorLookupTable errorLookupTable = new ErrorLookupTable();

            errorLookupTable.translate(ApplicationErrorCodes.INTERNET_ERROR, R.string.err_connectivity);
            errorLookupTable.translate(ErrorCodes.E_500, R.string.err_internal_server_error);

            return errorLookupTable;
        }
    }

    // ------------------------------------------------------------------------------------
    //case 5: n Distinct Query Param + n Repeatable query param.
    // http://google.com/register?username=a&password=b&email=c&kw=a&kw=b&kw=c
    public static class ReqDistinctAndRepeatableQueryParam {
        private final Map<String, String> mQueryParam;
        private final List<String> mKWParam;

        public ReqDistinctAndRepeatableQueryParam(Builder builder) {
            mQueryParam = builder.mQueryParam;
            mKWParam = builder.mKWParam;
        }

        public List<String> getKWParam() {
            return mKWParam;
        }

        // order the methods similar to the methods in the request interface parameters.
        public Map<String, String> getQueryParam() {
            return mQueryParam;
        }

        public static class Builder {

            private final String PARAM_USERNAME = "username";
            private final String PARAM_PASSWORD = "password";
            private final String PARAM_EMAIL = "email";
            private final Map<String, String> mQueryParam;
            private final List<String> mKWParam;

            public Builder() {
                mQueryParam = new HashMap<>();
                mKWParam = new ArrayList<>();
            }

            public Builder setUserName(String userName) {
                mQueryParam.put(PARAM_USERNAME, userName);
                return this;
            }

            public Builder setPassword(String password) {
                mQueryParam.put(PARAM_PASSWORD, password);
                return this;
            }

            public Builder setEmail(String email) {
                mQueryParam.put(PARAM_EMAIL, email);
                return this;
            }

            public Builder addKW(String kw) {
                mKWParam.add(kw);
                return this;
            }

            public ReqDistinctAndRepeatableQueryParam build() {
                return new ReqDistinctAndRepeatableQueryParam(this);
            }
        }
    }

    public static class ResDistinctAndRepeatableQueryParam {

    }

    public interface FolderGroupName5 {

        // named after last path param "make + {Last Path Segment} + Request"
        @GET("register")
        Call<ResDistinctAndRepeatableQueryParam> makeFindRequest(@QueryMap Map<String, String> query, @Query("kw") List<String> param);
    }


    public static class DistinctAndRepeatableQueryAPIRequest extends ApiRequest<ReqDistinctAndRepeatableQueryParam, ResDistinctAndRepeatableQueryParam, FolderGroupName5>{

        /**
         * Creates an instance of the api request.
         *
         * @param errorResponseTransformer transformer useful in case of resolving the application error.
         * @param serviceCreator           the configured service manager for creating the services instances.
         */
        public DistinctAndRepeatableQueryAPIRequest(ErrorResponseTransformer errorResponseTransformer, ServiceCreator serviceCreator) {
            super(errorResponseTransformer, serviceCreator);
        }

        @Override
        protected Class<FolderGroupName5> getServiceClass() {
            return FolderGroupName5.class;
        }

        @Override
        protected Call<ResDistinctAndRepeatableQueryParam> makeRequest(ReqDistinctAndRepeatableQueryParam reqSingleQuery, FolderGroupName5 folderGroupName) {
            return folderGroupName.makeFindRequest(reqSingleQuery.getQueryParam(), reqSingleQuery.getKWParam());
        }

        @Override
        protected ErrorLookupTable prepareErrorLookupMessages() {
            ErrorLookupTable errorLookupTable = new ErrorLookupTable();

            errorLookupTable.translate(ApplicationErrorCodes.INTERNET_ERROR, R.string.err_connectivity);
            errorLookupTable.translate(ErrorCodes.E_500, R.string.err_internal_server_error);

            return errorLookupTable;
        }
    }

    // http://someurl.com?
}
