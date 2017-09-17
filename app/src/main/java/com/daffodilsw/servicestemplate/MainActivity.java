package com.daffodilsw.servicestemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.daffodilsw.servicestemplate.session.UserSession;
import com.libapi.ErrorResponse;
import com.libapi.ResponseCallback;
import com.libpersistance.SessionManager;
import com.libservices.post.formdata.PostFormDataApiRequest;
import com.libservices.post.formdata.ReqPostFormData;
import com.libservices.post.formdata.ResPostFormData;
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

        ReqPostFormData reqPostFormData = new ReqPostFormData.Builder()
                .withEmail("shubhang.malviya@daffodilsw.com")
                .build();

        PostFormDataApiRequest postRawApiRequest = new PostFormDataApiRequest(requestProvider.getServiceCreator(), requestProvider.getErrorResponseTransformer());
        postRawApiRequest.makeRequest(reqPostFormData, new ResponseCallback<ResPostFormData>() {
            @Override
            public void onSuccess(ResPostFormData data) {

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
