package com.libapi;

import okhttp3.MultipartBody;

import java.io.File;

public class MultipartBodyCreator {

    private final MultipartBody.Builder mMultipartBuilder;

    public MultipartBodyCreator() {
        mMultipartBuilder = new MultipartBody.Builder();
    }

    public MultipartBodyCreator addPart(String name, String value) {
        mMultipartBuilder.addFormDataPart(name, null, Encoder.getPlainText(value));
        return this;
    }

    public MultipartBodyCreator addFilePart(String partName,String path, UploadCallbacks uploadCallbacks) {
        File file = new File(path);
        ProgressRequestBody requestBody = new ProgressRequestBody(file, uploadCallbacks);
        mMultipartBuilder.addFormDataPart(partName, file.getName(), requestBody);
        return this;
    }

    public MultipartBody create() {
        return mMultipartBuilder.build();
    }

}
