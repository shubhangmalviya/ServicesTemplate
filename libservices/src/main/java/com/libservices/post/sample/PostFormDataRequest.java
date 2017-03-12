package com.libservices.post.sample;

import com.libapi.request.FormDataRequestBuilder;
import com.libapi.request.providers.HeaderProvider;
import com.libapi.request.providers.DistinctQueryProvider;
import com.libapi.request.providers.RequestBodyProvider;
import com.libapi.UploadCallbacks;
import com.libapi.request.Header;
import com.libapi.request.QueryParam;

import okhttp3.RequestBody;

public class PostFormDataRequest implements RequestBodyProvider, DistinctQueryProvider, HeaderProvider {

    private static final String PART_USERNAME = "username";
    private static final String PART_EMAIL = "email";
    private static final String PART_PASSWORD = "password";
    private static final String PART_TYPE = "type";
    private static final String PART_PREFERRED_LANGUAGE = "preferred_language";
    private static final String PART_MOBILE = "mobile";
    private static final String PART_USER_PICTURE = "user_picture";
    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ARRAY = "a[]";
    private static final String HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded";

    private final FormDataRequestBuilder mFormDataRequestBuilder;
    private final QueryParam.DistinctParamBuilder mQueryParamDistinctParamBuilder;
    private final Header.Builder mHeaderBuilder;

    public PostFormDataRequest() {
        mFormDataRequestBuilder = new FormDataRequestBuilder();
        mQueryParamDistinctParamBuilder = new QueryParam.DistinctParamBuilder();
        mHeaderBuilder = new Header.Builder();
    }

    public PostFormDataRequest withUsername(String username) {
        mFormDataRequestBuilder.addPart(PART_USERNAME, username);
        return this;
    }

    public PostFormDataRequest withEmail(String email) {
        mFormDataRequestBuilder.addPart(PART_EMAIL, email);
        return this;
    }

    public PostFormDataRequest withPassword(String password) {
        mFormDataRequestBuilder.addPart(PART_PASSWORD, password);
        return this;
    }

    public void withType(String type) {
        mFormDataRequestBuilder.addPart(PART_TYPE, type);
    }

    public void withPreferredLanguage(String preferredLanguage) {
        mFormDataRequestBuilder.addPart(PART_PREFERRED_LANGUAGE, preferredLanguage);
    }

    public void withMobile(String mobile) {
        mFormDataRequestBuilder.addPart(PART_MOBILE, mobile);
    }

    public PostFormDataRequest addImage(String path, UploadCallbacks uploadCallbacks) {
        mFormDataRequestBuilder.addFilePart(PART_USER_PICTURE, path, uploadCallbacks);
        return this;
    }

    public PostFormDataRequest addId(String value) {
        mQueryParamDistinctParamBuilder.addQueryParam(PARAM_ID, value);
        return this;
    }

    public PostFormDataRequest addName(String value) {
        mQueryParamDistinctParamBuilder.addQueryParam(PARAM_NAME, value);
        return this;
    }

    public PostFormDataRequest addContentType(String value) {
        mHeaderBuilder.addKeyValue(HEADER_CONTENT_TYPE, value);
        return this;
    }

    @Override
    public RequestBody provideRequestBody() {
        return mFormDataRequestBuilder.build();
    }

    @Override
    public Header provideHeader() {
        return mHeaderBuilder.build();
    }

    @Override
    public QueryParam provideQueryParam() {
        return mQueryParamDistinctParamBuilder.build();
    }
}
