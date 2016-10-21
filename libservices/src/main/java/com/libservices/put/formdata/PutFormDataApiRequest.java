package com.libservices.put.formdata;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class PutFormDataApiRequest extends ApiRequest<ReqSampleFormData, ResFormData, FormDataService> {

    public PutFormDataApiRequest(ServiceCreator serviceCreator,
                                 ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<FormDataService> getServiceClass() {
        return FormDataService.class;
    }

    @Override
    protected Call<ResFormData> makeRequest(ReqSampleFormData reqSampleFormData, FormDataService beeahServices) {
        return beeahServices.makeFormDataRequest(reqSampleFormData.getFilePart(),
                reqSampleFormData.getRequestBodyMap());
    }
}
