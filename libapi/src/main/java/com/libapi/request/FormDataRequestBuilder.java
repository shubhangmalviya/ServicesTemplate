package com.libapi.request;

import com.libapi.Encoder;
import com.libapi.ProgressRequestBody;
import com.libapi.UploadCallbacks;

import java.io.File;

import okhttp3.MultipartBody;

public class FormDataRequestBuilder {

    private final MultipartBody.Builder mMultipartBuilder;

    public FormDataRequestBuilder() {
        mMultipartBuilder = new MultipartBody.Builder();
    }

    public FormDataRequestBuilder addPart(String name, String value) {
        mMultipartBuilder.addFormDataPart(name, null, Encoder.getPlainText(value));
        return this;
    }

    public FormDataRequestBuilder addFilePart(String partName, String path, UploadCallbacks uploadCallbacks) {
        File file = new File(path);
        ProgressRequestBody requestBody = new ProgressRequestBody(file, uploadCallbacks);
        mMultipartBuilder.addFormDataPart(partName, file.getName(), requestBody);
        return this;
    }

    public MultipartBody build() {
        return mMultipartBuilder.build();
    }

}
