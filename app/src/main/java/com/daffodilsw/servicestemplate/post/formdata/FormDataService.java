package com.daffodilsw.servicestemplate.post.formdata;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface FormDataService {

    @Multipart
    @POST
    Call<ResFormData> makeFormDataRequest(@Url String url, @Part List<MultipartBody.Part> filePart,
                             @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @POST
    Call<ResFormData> makeFormDataRequest(@Url String url, @Part MultipartBody.Part partData);

}
