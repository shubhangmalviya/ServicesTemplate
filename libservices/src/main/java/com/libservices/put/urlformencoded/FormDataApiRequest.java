package com.libservices.put.urlformencoded;

import com.libapi.ApiRequest;
import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class FormDataApiRequest extends ApiRequest<ReqFormEncodedData, ResFormEncodedData, FormUrlEncodedService> {

    public FormDataApiRequest(ServiceCreator serviceCreator,
                              ErrorResponseTransformer errorResponseTransformer) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<FormUrlEncodedService> getServiceClass() {
        return FormUrlEncodedService.class;
    }

    @Override
    protected Call<ResFormEncodedData> makeRequest(ReqFormEncodedData reqFormEncodedData, FormUrlEncodedService beeahServices) {
        return beeahServices.makeFormEncodedRequest(reqFormEncodedData.getRequestBodyMap());
    }

    @Override
    protected ErrorLookupTable prepareErrorLookupMessages() {
        return null;
    }
}
