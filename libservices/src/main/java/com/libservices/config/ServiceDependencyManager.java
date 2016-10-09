package com.libservices.config;

import com.libapi.ErrorLookupTable;
import com.libapi.ServiceCreator;
import com.libpersistance.SessionManager;

import retrofit2.Retrofit;

public class ServiceDependencyManager {

    private final SessionManager mSessionManager;

    public ServiceDependencyManager(SessionManager sessionManager) {
        mSessionManager = sessionManager;
    }

    public void setUp() {

        HeaderModifier headerModifier = new HeaderModifier(mSessionManager);
        HeaderRequestInterceptor interceptor = new HeaderRequestInterceptor(headerModifier);

        RetrofitConfigurationManager configurationManager = new RetrofitConfigurationManager();
        Retrofit retrofit = configurationManager.createRetrofit("http://someurl/", interceptor);

        HttpErrorLookupComposer composer = new HttpErrorLookupComposer();
        ErrorLookupTable errorLookupTable = new ErrorLookupTable();
        composer.addStandardErrorLookup(errorLookupTable);

        ServiceCreator serviceCreator = new ServiceCreator(retrofit);
        AppErrorResponseTransformer errorResponseTransformer = new AppErrorResponseTransformer(errorLookupTable);


    }


}
