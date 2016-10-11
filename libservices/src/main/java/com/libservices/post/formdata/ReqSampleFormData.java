package com.libservices.post.formdata;

import com.libapi.APIUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ReqSampleFormData {

    private final Map<String, RequestBody> mRequestBodyMap;
    private final List<MultipartBody.Part> mFilePart;
    private  String mEndPoint;

    private ReqSampleFormData(Builder builder) {
        mRequestBodyMap = builder.mRequestBodyMap;
        mFilePart = builder.mFilePart;
        mEndPoint = builder.mEndPoint;
    }

    public Map<String, RequestBody> getRequestBodyMap() {
        return mRequestBodyMap;
    }

    public List<MultipartBody.Part> getFilePart() {
        return mFilePart;
    }

    public String getEndPoint() {
        return mEndPoint;
    }

    public static final class Builder {

        private static final String PART_USER_ID = "user_id";
        private static final String PART_IMAGES = "images[]";
        private static final String PART_LONGITUDE = "longitude";

        private final MultipartFileBodyCreator.Builder mFileBuilder;
        private final Map<String, RequestBody> mRequestBodyMap;
        private List<MultipartBody.Part> mFilePart;
        private final String mEndPointFormat = "/feeds/%s/comments";
        private  String mEndPoint;

        public Builder(String feedId) {
            this.mRequestBodyMap = new HashMap<>();
            mEndPoint = String.format(mEndPointFormat, feedId);
            mFileBuilder = new MultipartFileBodyCreator.Builder(PART_IMAGES);
        }


        public Builder withUserId(String userId) {
            mRequestBodyMap.put(PART_USER_ID, APIUtils.createBodyWithText(userId));
            return this;
        }

        public Builder withLongitude(String latitude) {
            mRequestBodyMap.put(PART_LONGITUDE, APIUtils.createBodyWithText(latitude));
            return this;
        }

        public Builder addImage(String path) {
            mFileBuilder.addFilePath(path);
            return this;
        }

        public Builder addAllImages(List<String> paths) {
            for (String path : paths) {
                addImage(path);
            }
            return this;
        }

        public ReqSampleFormData build() {
            mFilePart = mFileBuilder.build().createFilePart();
            return new ReqSampleFormData(this);
        }
    }
}
