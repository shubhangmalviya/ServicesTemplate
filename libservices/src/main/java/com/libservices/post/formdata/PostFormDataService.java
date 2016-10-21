package com.libservices.post.formdata;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface PostFormDataService {

    String PATH = "path";

    @Multipart
    @POST(PATH)
    Call<ResPostFormData> makeFormDataRequest(@Part List<MultipartBody.Part> filePart,
                                              @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @POST(PATH)
    Call<ResPostFormData> makeFormDataRequest(@Part MultipartBody.Part partData);

}
