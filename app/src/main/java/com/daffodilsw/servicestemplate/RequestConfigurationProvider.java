package com.daffodilsw.servicestemplate;

import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponseTransformer;
import com.libapi.ServiceCreator;
import com.libservices.core.AppErrorResponseTransformer;
import com.libservices.core.HttpErrorLookupComposer;

import retrofit2.Retrofit;

public class RequestConfigurationProvider {

    private final ServiceCreator mServiceCreator;
    private ErrorResponseTransformer mErrorResponseTransformer;

    public RequestConfigurationProvider(Retrofit retrofit) {
        mServiceCreator = new ServiceCreator(retrofit);
        HttpErrorLookupComposer composer = new HttpErrorLookupComposer();
        ErrorLookupTable errorLookupTable = new ErrorLookupTable();
        composer.addStandardErrorLookup(errorLookupTable);
        mErrorResponseTransformer = new AppErrorResponseTransformer(errorLookupTable);
    }

    public ServiceCreator getServiceCreator() {
        return mServiceCreator;
    }

    public ErrorResponseTransformer getErrorResponseTransformer() {
        return mErrorResponseTransformer;
    }
}
