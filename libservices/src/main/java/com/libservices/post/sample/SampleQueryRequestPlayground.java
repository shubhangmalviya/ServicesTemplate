package com.libservices.post.sample;

import com.libapi.ApiRequest;
import com.libapi.ApplicationErrorCodes;
import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import com.libservices.R;
import com.libservices.core.ErrorCodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
    public static class nDistinctQueryBuilder {

    }

    // ------------------------------------------------------------------------------------
    // case 3: repeatable query param.
    public static class RepeatableQueryBuilder {

    }

    // ------------------------------------------------------------------------------------
    //case 4: Single Query param + n Repeatable Query Param.
    public static class SingleAndRepeatableQueryParam {

    }

    // ------------------------------------------------------------------------------------
    //case 5: n Distinct Query Param + n Repeatable query param.
    public static class DistinctAndRepeatableQueryParam {

    }

    // http://someurl.com?
}
