package com.daffodilsw.servicestemplate.post.raw;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;

import retrofit2.Call;

public class RawApiRequest extends ApiRequest<ReqRawData, ResRawData> {

    public RawApiRequest(ErrorResponseTransformer errorResponseTransformer,
                         ServiceCreator serviceCreator) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Call<ResRawData> makeRequest(ReqRawData reqRawData, ServiceCreator serviceCreator) {
        RawDataService service = serviceCreator.createService(RawDataService.class);
        return service.postRequest(reqRawData);
    }
}
