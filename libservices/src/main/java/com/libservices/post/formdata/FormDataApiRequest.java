package com.libservices.post.formdata;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;

import retrofit2.Call;

public class FormDataApiRequest extends ApiRequest<ReqSampleFormData, ResFormData, FormDataService> {

    public FormDataApiRequest(ServiceCreator serviceCreator,
                              ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<FormDataService> getServiceClass() {
        return FormDataService.class;
    }

    @Override
    protected Call<ResFormData> makeRequest(ReqSampleFormData reqSampleFormData, FormDataService beeahServices) {
        return beeahServices.makeFormDataRequest(reqSampleFormData.getEndPoint(), reqSampleFormData.getFilePart(),
                reqSampleFormData.getRequestBodyMap());
    }
}
