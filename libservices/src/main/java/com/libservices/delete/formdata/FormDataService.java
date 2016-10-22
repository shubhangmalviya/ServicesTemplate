package com.libservices.delete.formdata;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface FormDataService {

    String PATH = "path";

    @Multipart
    @HTTP(method = "DELETE", path = PATH, hasBody = true)
    Call<ResFormData> makeFormDataRequest(@Part List<MultipartBody.Part> filePart,
                                          @PartMap Map<String, RequestBody> requestBodyMap);

    @Multipart
    @HTTP(method = "DELETE", path = PATH, hasBody = true)
    Call<ResFormData> makeFormDataRequest(@Part MultipartBody.Part partData);

}
