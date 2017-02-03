package com.libservices.post.formdata;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface PostFormDataService {

    String PATH = "path";

    @Multipart
    @POST(value = PATH)
    Call<ResPostFormData> makeFormDataRequest(@Part List<MultipartBody.Part> filePart,
                                              @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @POST(PATH)
    Call<ResPostFormData> makeFormDataRequest(@Part MultipartBody.Part partData);

}
