package com.libservices.post.formdata;

import com.libapi.ApiRequest;
import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;

import retrofit2.Call;

public class PostFormDataApiRequest extends ApiRequest<ReqPostFormData, ResPostFormData, PostFormDataService> {

    public PostFormDataApiRequest(ServiceCreator serviceCreator,
                                  ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<PostFormDataService> getServiceClass() {
        return PostFormDataService.class;
    }

    @Override
    protected Call<ResPostFormData> makeRequest(ReqPostFormData reqPostFormData, PostFormDataService beeahServices) {
        return beeahServices.makeFormDataRequest(reqPostFormData.getMultipartBody());
    }

    @Override
    protected ErrorLookupTable prepareErrorLookupMessages() {
        return null;
    }
}
