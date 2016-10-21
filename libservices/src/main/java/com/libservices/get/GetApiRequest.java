package com.libservices.get;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class GetApiRequest extends ApiRequest<ReqGetData, ResGetData, GetDataService> {

    public GetApiRequest(ErrorResponseTransformer errorResponseTransformer,
                         ServiceCreator serviceCreator) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<GetDataService> getServiceClass() {
        return GetDataService.class;
    }

    @Override
    protected Call<ResGetData> makeRequest(ReqGetData reqGetData, GetDataService getDataService) {
        return getDataService.getUserQueryParam(reqGetData.getQueryMap());
    }
}
