package com.daffodilsw.servicestemplate.post.formdata;

import com.daffodilsw.servicestemplate.BuildConfig;
import com.libapi.APIUtils;
import com.libapi.ApiRequest;
import com.libapi.ErrorMessageResolver;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ResponseCallback;
import com.libapi.ResponseWrapper;
import com.libapi.ServiceManager;

import retrofit2.Call;

public class FormDataApiRequest implements ApiRequest {

    private Call<ResFormData> mResFormDataCall;
    private final ErrorMessageResolver mErrorMessageResolver;
    private final ErrorResponseTransformer mErrorResponseTransformer;

    public FormDataApiRequest(ErrorMessageResolver errorMessageResolver,
                              ErrorResponseTransformer errorResponseTransformer) {
        mErrorMessageResolver = errorMessageResolver;
        mErrorResponseTransformer = errorResponseTransformer;
    }

    public void makeRequest(ReqSampleFormData reqSampleFormData
            , ResponseCallback<ResFormData> responseCallback) {

        FormDataService beeahServices = new ServiceManager().
                createService(FormDataService.class, BuildConfig.BASE_URL);

        mResFormDataCall = beeahServices.makeFormDataRequest(reqSampleFormData.getEndPoint(), reqSampleFormData.getFilePart(),
                reqSampleFormData.getRequestBodyMap());

        mResFormDataCall.enqueue(new ResponseWrapper<>(mErrorMessageResolver, responseCallback, mErrorResponseTransformer));
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
