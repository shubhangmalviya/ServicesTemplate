package com.libservices.post.formdata;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqSampleFormData {

    private final Map<String, RequestBody> mRequestBodyMap;
    private final List<MultipartBody.Part> mFilePart;

    private ReqSampleFormData(Builder builder) {
        mRequestBodyMap = builder.mRequestBodyMap;
        mFilePart = builder.mFilePartList;
    }

    public Map<String, RequestBody> getRequestBodyMap() {
        return mRequestBodyMap;
    }

    public List<MultipartBody.Part> getFilePart() {
        return mFilePart;
    }

    public static final class Builder {

        private static final String PART_USER_ID = "user_id";
        private static final String PART_IMAGES = "images[]";
        private static final String PART_LONGITUDE = "longitude";
        public static final String MULTIPART_FORM_DATA = "multipart/form-data";

        private final List<MultipartBody.Part> mFilePartList;
        private final Map<String, RequestBody> mRequestBodyMap;

        public Builder(String feedId) {
            this.mRequestBodyMap = new HashMap<>();
            mFilePartList = new ArrayList<>();
        }


        public Builder withUserId(String userId) {
            mRequestBodyMap.put(PART_USER_ID, RequestBody.create(MediaType.parse("text/plain"), userId));
            return this;
        }

        public Builder withLongitude(String latitude) {
            mRequestBodyMap.put(PART_LONGITUDE, RequestBody.create(MediaType.parse("text/plain"), latitude));
            return this;
        }

        // TODO somehow notify the listeners that upload is going to happen.
        // TODO use progress upload classes.
        public Builder addImage(String path) {
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
            mFilePartList.add(MultipartBody.Part.createFormData(PART_IMAGES, file.getName(), requestBody));
            return this;
        }

        public Builder addAllImages(List<String> paths) {
            for (String path : paths) {
                addImage(path);
            }
            return this;
        }

        public ReqSampleFormData build() {
            return new ReqSampleFormData(this);
        }
    }
}
