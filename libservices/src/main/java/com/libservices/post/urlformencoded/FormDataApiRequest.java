package com.libservices.post.urlformencoded;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class FormDataApiRequest extends ApiRequest<ReqSampleFormData, ResFormData, FormUrlEncodedService> {

    public FormDataApiRequest(ServiceCreator serviceCreator,
                              ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<FormUrlEncodedService> getServiceClass() {
        return FormUrlEncodedService.class;
    }

    @Override
    protected Call<ResFormData> makeRequest(ReqSampleFormData reqSampleFormData, FormUrlEncodedService beeahServices) {
        return beeahServices.makeFormEncodedRequest(reqSampleFormData.getRequestBodyMap());
    }
}
