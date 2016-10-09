package com.daffodilsw.servicestemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.libpersistance.SessionManager;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionProvider sessionProvider = new SessionProvider(this);
        SessionManager sessionManager = sessionProvider.provideSessionManager();
        RetrofitProvider retrofitProvider = new RetrofitProvider(sessionManager);
        Retrofit retrofit = retrofitProvider.provideRetrofit();
        RequestConfigurationProvider requestProvider = new RequestConfigurationProvider(retrofit);
    }
}
