package com.libapi;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Encoder {

    public static RequestBody getPlainText(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

}
