package com.libservices.delete.raw;

import com.libapi.ApiRequest;
import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class RawApiRequest extends ApiRequest<ReqRawData, ResRawData, RawDataService> {

    public RawApiRequest(ErrorResponseTransformer errorResponseTransformer,
                         ServiceCreator serviceCreator) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<RawDataService> getServiceClass() {
        return RawDataService.class;
    }

    @Override
    protected Call<ResRawData> makeRequest(ReqRawData reqRawData, RawDataService rawDataService) {
        return rawDataService.postRequest(reqRawData);
    }

    @Override
    protected ErrorLookupTable prepareErrorLookupMessages() {
        return null;
    }
}
