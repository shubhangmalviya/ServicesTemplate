package com.libservices.put.formdata;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface FormDataService {

    String PATH = "path";

    @Multipart
    @PUT(PATH)
    Call<ResFormData> makeFormDataRequest(@Part List<MultipartBody.Part> filePart,
                                          @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @PUT(PATH)
    Call<ResFormData> makeFormDataRequest(@Part MultipartBody.Part partData);

}
