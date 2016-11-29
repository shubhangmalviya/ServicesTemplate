package com.libservices.post.formdata;

import com.libapi.ProgressRequestBody;
import com.libapi.UploadCallbacks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ReqPostFormData {


    private final Map<String, RequestBody> mRequestBodyMap;
    private final List<MultipartBody.Part> mFilePart;

    private ReqPostFormData(Builder requestBuilder) {
        mRequestBodyMap = requestBuilder.mRequestBodyMap;
        mFilePart = requestBuilder.mFilePartList;
    }

    public List<MultipartBody.Part> getFilePart() {
        return mFilePart;
    }

    public Map<String, RequestBody> getRequestBodyMap() {
        return mRequestBodyMap;
    }

    public static final class Builder {
        public static final String PART_USERNAME = "username";

        public static final String PART_EMAIL = "email";

        public static final String PART_PASSWORD = "password";

        public static final String PART_TYPE = "type";

        public static final String PART_PREFERRED_LANGUAGE = "preferred_language";

        public static final String PART_MOBILE = "mobile";

        public static final String PART_USER_PICTURE = "user_picture";

        private final List<MultipartBody.Part> mFilePartList;
        private final Map<String, RequestBody> mRequestBodyMap;

        public Builder() {
            mRequestBodyMap = new HashMap<>();
            mFilePartList = new ArrayList<>();
        }

        public Builder withUsername(String username) {
            mRequestBodyMap.put(PART_USERNAME, RequestBody.create(MediaType.parse("text/plain"), username));
            return this;
        }

        public void withEmail(String email) {
            mRequestBodyMap.put(PART_EMAIL, RequestBody.create(MediaType.parse("text/plain"), email));
        }

        public void withPassword(String password) {
            mRequestBodyMap.put(PART_PASSWORD, RequestBody.create(MediaType.parse("text/plain"), password));
        }

        public void withType(String type) {
            mRequestBodyMap.put(PART_TYPE, RequestBody.create(MediaType.parse("text/plain"), type));
        }

        public void withPreferredLanguage(String preferredLanguage) {
            mRequestBodyMap.put(PART_PREFERRED_LANGUAGE, RequestBody.create(MediaType.parse("text/plain"), preferredLanguage));
        }

        public void withMobile(String mobile) {
            mRequestBodyMap.put(PART_MOBILE, RequestBody.create(MediaType.parse("text/plain"), mobile));
        }

        public Builder addImage(String path, UploadCallbacks uploadCallbacks) {
            File file = new File(path);
            ProgressRequestBody requestBody = new ProgressRequestBody(file, uploadCallbacks);
            mFilePartList.add(MultipartBody.Part.createFormData(PART_USER_PICTURE, file.getName(), requestBody));
            return this;
        }

        public ReqPostFormData build() {
            return new ReqPostFormData(this);
        }
    }
}
