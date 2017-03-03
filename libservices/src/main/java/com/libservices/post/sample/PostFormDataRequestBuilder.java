package com.libservices.post.sample;

import com.libapi.MultipartBodyCreator;
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

    private final MultipartBodyCreator mMultipartBodyCreator;

    public PostFormDataRequestBuilder() {
        mMultipartBodyCreator = new MultipartBodyCreator();
    }

    public PostFormDataRequestBuilder withUsername(String username) {
        mMultipartBodyCreator.addPart(PART_USERNAME, username);
        return this;
    }

    public void withEmail(String email) {
        mMultipartBodyCreator.addPart(PART_EMAIL, email);
    }

    public void withPassword(String password) {
        mMultipartBodyCreator.addPart(PART_PASSWORD, password);
    }

    public void withType(String type) {
        mMultipartBodyCreator.addPart(PART_TYPE, type);
    }

    public void withPreferredLanguage(String preferredLanguage) {
        mMultipartBodyCreator.addPart(PART_PREFERRED_LANGUAGE, preferredLanguage);
    }

    public void withMobile(String mobile) {
        mMultipartBodyCreator.addPart(PART_MOBILE, mobile);
    }

    public PostFormDataRequestBuilder addImage(String path, UploadCallbacks uploadCallbacks) {
        mMultipartBodyCreator.addFilePart(PART_USER_PICTURE, path, uploadCallbacks);
        return this;
    }

    public RequestBody build() {
        return mMultipartBodyCreator.create();
    }
}
