package com.daffodilsw.servicestemplate.post.formdata;

import com.daffodilsw.servicestemplate.*;
import retrofit2.Call;

public class FormDataApiRequest implements ApiRequest{

    Call<ResFormData> mResFormDataCall;

    public void makeRequest(ReqSampleFormData reqSampleFormData, ErrorMessageResolver errorMessageResolver
            , ResponseCallback<ResFormData> responseCallback) {

        Service beeahServices = ServiceManager.get().createService(Service.class);
        mResFormDataCall = beeahServices.makeFormDataRequest(ServiceManager.SERVICE_ENDPOINT + reqSampleFormData.getEndPoint(), reqSampleFormData.getFilePart(),
                reqSampleFormData.getRequestBodyMap());
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
