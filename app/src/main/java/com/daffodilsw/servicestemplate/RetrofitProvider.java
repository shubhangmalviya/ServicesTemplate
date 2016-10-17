package com.daffodilsw.servicestemplate;

import com.libpersistance.SessionManager;
import com.libservices.config.HeaderRequestInterceptor;
import com.libservices.config.RetrofitConfigurationManager;

import retrofit2.Retrofit;

public class RetrofitProvider {

    private final Retrofit mRetrofit;

    public RetrofitProvider(SessionManager sessionManager) {
        HeaderRequestInterceptor interceptor = new HeaderRequestInterceptor(sessionManager);

        RetrofitConfigurationManager configurationManager = new RetrofitConfigurationManager();
        mRetrofit = configurationManager.createRetrofit(BuildConfig.BASE_URL, interceptor);
    }

    public Retrofit provideRetrofit() {
        return mRetrofit;
    }

}
