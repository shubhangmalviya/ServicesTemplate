package com.libservices.delete.urlformencoded;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class FormDataApiRequest extends ApiRequest<ReqFormEncodedData, ResDeleteFormEncodedData, FormUrlEncodedService> {

    public FormDataApiRequest(ServiceCreator serviceCreator,
                              ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<FormUrlEncodedService> getServiceClass() {
        return FormUrlEncodedService.class;
    }

    @Override
    protected Call<ResDeleteFormEncodedData> makeRequest(ReqFormEncodedData reqFormEncodedData, FormUrlEncodedService beeahServices) {
        return beeahServices.makeFormEncodedRequest(reqFormEncodedData.getRequestBodyMap());
    }
}
