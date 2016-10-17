package com.daffodilsw.servicestemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.daffodilsw.servicestemplate.session.UserSession;
import com.libapi.ErrorResponse;
import com.libapi.ResponseCallback;
import com.libpersistance.SessionManager;
import com.libservices.post.raw.RawApiRequest;
import com.libservices.post.raw.ResRawData;

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
        RawApiRequest rawApiRequest = new RawApiRequest(requestProvider.getErrorResponseTransformer()
                , requestProvider.getServiceCreator());
        rawApiRequest.makeRequest(null, new ResponseCallback<ResRawData>() {
            @Override
            public void onSuccess(ResRawData data) {

            }

            @Override
            public void onFailure(ErrorResponse errorResponse) {

            }
        });


        UserSession session = (UserSession) sessionManager.readSession();
        UserSession newSession = UserSession.newBuilder(session)
                .withAuthToken("dhcdshjcb")
                .build();

        sessionManager.storeSession(newSession);
    }
}
