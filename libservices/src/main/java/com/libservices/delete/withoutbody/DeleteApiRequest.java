package com.libservices.delete.withoutbody;

import com.libapi.ApiRequest;
import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import retrofit2.Call;

public class DeleteApiRequest extends ApiRequest<ReqDeleteData, ResDeleteData, DeleteDataService> {

    public DeleteApiRequest(ErrorResponseTransformer errorResponseTransformer,
                            ServiceCreator serviceCreator) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<DeleteDataService> getServiceClass() {
        return DeleteDataService.class;
    }

    @Override
    protected Call<ResDeleteData> makeRequest(ReqDeleteData reqDeleteData, DeleteDataService deleteDataService) {
        return deleteDataService.getUserQueryParam(reqDeleteData.getQueryMap());
    }

    @Override
    protected ErrorLookupTable prepareErrorLookupMessages() {
        return null;
    }
}
