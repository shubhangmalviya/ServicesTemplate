package com.libservices.post.sample;

import com.libapi.request.FormDataRequestBuilder;
import com.libapi.RequestBodyBuilder;
import com.libapi.UploadCallbacks;
import okhttp3.RequestBody;

public class PostFormDataRequestBuilder implements RequestBodyBuilder {

    private static final String PART_USERNAME = "username";
    private static final String PART_EMAIL = "email";
    private static final String PART_PASSWORD = "password";
    private static final String PART_TYPE = "type";
    private static final String PART_PREFERRED_LANGUAGE = "preferred_language";
    private static final String PART_MOBILE = "mobile";
    private static final String PART_USER_PICTURE = "user_picture";

    private final FormDataRequestBuilder mFormDataRequestBuilder;

    public PostFormDataRequestBuilder() {
        mFormDataRequestBuilder = new FormDataRequestBuilder();
    }

    public PostFormDataRequestBuilder withUsername(String username) {
        mFormDataRequestBuilder.addPart(PART_USERNAME, username);
        return this;
    }

    public void withEmail(String email) {
        mFormDataRequestBuilder.addPart(PART_EMAIL, email);
    }

    public void withPassword(String password) {
        mFormDataRequestBuilder.addPart(PART_PASSWORD, password);
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

    public PostFormDataRequestBuilder addImage(String path, UploadCallbacks uploadCallbacks) {
        mFormDataRequestBuilder.addFilePart(PART_USER_PICTURE, path, uploadCallbacks);
        return this;
    }

    public RequestBody build() {
        return mFormDataRequestBuilder.build();
    }
}
