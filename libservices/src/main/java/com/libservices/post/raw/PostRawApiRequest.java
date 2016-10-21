package com.libservices.post.raw;

import com.libapi.ApiRequest;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;

import retrofit2.Call;

public class PostRawApiRequest extends ApiRequest<ReqPostRawData, ResPostRawData, PostRawDataService> {

    public PostRawApiRequest(ErrorResponseTransformer errorResponseTransformer,
                             ServiceCreator serviceCreator) {
        super(errorResponseTransformer, serviceCreator);
    }

    @Override
    protected Class<PostRawDataService> getServiceClass() {
        return PostRawDataService.class;
    }

    @Override
    protected Call<ResPostRawData> makeRequest(ReqPostRawData reqPostRawData, PostRawDataService postRawDataService) {
        return postRawDataService.postRequest(reqPostRawData);
    }
}
