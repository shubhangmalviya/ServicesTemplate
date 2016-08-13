package com.daffodilsw.servicestemplate.post.formdata;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface Service {

    @Multipart
    @POST
    Call<ResFormData> makeFormDataRequest(@Url String url, @Part List<MultipartBody.Part> filePart,
                             @PartMap Map<String, RequestBody> requestBodyMap);
}
