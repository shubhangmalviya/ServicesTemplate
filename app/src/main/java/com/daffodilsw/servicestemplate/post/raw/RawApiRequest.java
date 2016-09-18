package com.daffodilsw.servicestemplate.post.raw;

import com.daffodilsw.servicestemplate.post.formdata.ResFormData;
import com.libapi.APIUtils;
import com.libapi.ApiRequest;
import com.libapi.ErrorMessageResolver;
import com.libapi.ResponseCallback;

import retrofit2.Call;

public class RawApiRequest implements ApiRequest {

    Call<ResFormData> mResFormDataCall;

    public void makeRequest(ReqRawData reqRawData, ErrorMessageResolver errorMessageResolver
            , ResponseCallback<ResFormData> responseCallback) {

//        FormDataService beeahServices = ServiceManager.get().createService(FormDataService.class);
//        mResFormDataCall = beeahServices.makeFormDataRequest(ServiceManager.SERVICE_ENDPOINT + reqRawData.getPath(), null,
//                reqRawData.getReqBody());
//        mResFormDataCall.enqueue(new ResponseWrapper<>(errorMessageResolver, responseCallback));

    }

    @Override
    public void cancel() {
        APIUtils.cancelAPIRequest(mResFormDataCall);
        if (APIUtils.shouldDisposeAPICall(mResFormDataCall)) {
            mResFormDataCall = null;
        }
    }

    @Override
    public boolean isInProgress() {
        return APIUtils.isAPICallInProgress(mResFormDataCall);
    }
}
