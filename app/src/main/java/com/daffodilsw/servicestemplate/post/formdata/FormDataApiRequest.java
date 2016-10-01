package com.daffodilsw.servicestemplate.post.formdata;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;

import retrofit2.Call;

public class FormDataApiRequest extends ApiRequest<ReqSampleFormData, ResFormData> {

    public FormDataApiRequest(ServiceCreator serviceCreator,
                              ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Call<ResFormData> makeRequest(ReqSampleFormData reqSampleFormData, ServiceCreator serviceCreator) {
        FormDataService beeahServices = serviceCreator.createService(FormDataService.class);

        return beeahServices.makeFormDataRequest(reqSampleFormData.getEndPoint(), reqSampleFormData.getFilePart(),
                reqSampleFormData.getRequestBodyMap());
    }
}
