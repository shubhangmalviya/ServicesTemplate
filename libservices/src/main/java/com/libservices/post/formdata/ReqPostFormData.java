package com.libservices.post.formdata;

import com.libapi.UploadCallbacks;
import com.libapi.request.FormDataRequestBuilder;
import okhttp3.MultipartBody;

public class ReqPostFormData {

    private final MultipartBody mMultipartBody;

    private ReqPostFormData(Builder requestBuilder) {
        mMultipartBody = requestBuilder.mMultipartBody;
    }

    public MultipartBody getMultipartBody() {
        return mMultipartBody;
    }

    public static final class Builder {
        public static final String PART_USERNAME = "username";

        public static final String PART_EMAIL = "email";

        public static final String PART_PASSWORD = "password";

        public static final String PART_TYPE = "type";

        public static final String PART_PREFERRED_LANGUAGE = "preferred_language";

        public static final String PART_MOBILE = "mobile";

        public static final String PART_USER_PICTURE = "user_picture";

        private MultipartBody mMultipartBody;
        private FormDataRequestBuilder mFormDataRequestBuilder;

        public Builder() {
            mFormDataRequestBuilder = new FormDataRequestBuilder();
        }

        public Builder withUsername(String username) {
            mFormDataRequestBuilder.addPart(PART_USERNAME, username);
            return this;
        }

        public Builder withEmail(String email) {
            mFormDataRequestBuilder.addPart(PART_EMAIL, email);
            return this;
        }

        public Builder withPassword(String password) {
            mFormDataRequestBuilder.addPart(PART_PASSWORD, password);
            return this;
        }

        public Builder withType(String type) {
            mFormDataRequestBuilder.addPart(PART_TYPE, type);
            return this;
        }

        public Builder withPreferredLanguage(String preferredLanguage) {
            mFormDataRequestBuilder.addPart(PART_PREFERRED_LANGUAGE, preferredLanguage);
            return this;
        }

        public Builder withMobile(String mobile) {
            mFormDataRequestBuilder.addPart(PART_MOBILE, mobile);
            return this;
        }

        public Builder addImage(String path, UploadCallbacks uploadCallbacks) {
            mFormDataRequestBuilder.addFilePart(PART_USER_PICTURE, path, uploadCallbacks);
            return this;
        }

        public ReqPostFormData build() {
            mMultipartBody = mFormDataRequestBuilder.build();
            return new ReqPostFormData(this);
        }
    }
}
