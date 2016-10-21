package com.libservices.delete.formdata;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

import java.util.List;
import java.util.Map;

public interface FormDataService {

    String PATH = "path";

    @Multipart
    @POST(PATH)
    Call<ResFormData> makeFormDataRequest(@Part List<MultipartBody.Part> filePart,
                                          @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @POST(PATH)
    Call<ResFormData> makeFormDataRequest(@Part MultipartBody.Part partData);

}
