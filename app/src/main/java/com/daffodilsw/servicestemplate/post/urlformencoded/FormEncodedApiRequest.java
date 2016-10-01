package com.daffodilsw.servicestemplate.post.urlformencoded;

import com.daffodilsw.servicestemplate.post.raw.RawDataService;
import com.daffodilsw.servicestemplate.post.raw.ReqRawData;
import com.daffodilsw.servicestemplate.post.raw.ResRawData;
import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;

import retrofit2.Call;

public class FormEncodedApiRequest extends ApiRequest<ReqRawData, ResRawData> {

    public FormEncodedApiRequest(ErrorResponseTransformer errorResponseTransformer, ServiceCreator serviceCreator) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Call<ResRawData> makeRequest(ReqRawData reqRawData, ServiceCreator serviceCreator) {
        RawDataService service = serviceCreator.createService(RawDataService.class);
        return service.postRequest(reqRawData);
    }

}
