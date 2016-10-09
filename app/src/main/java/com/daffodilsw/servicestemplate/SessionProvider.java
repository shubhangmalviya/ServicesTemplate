package com.daffodilsw.servicestemplate;

import android.content.Context;

import com.daffodilsw.servicestemplate.session.SessionDataStore;
import com.libpersistance.SessionManager;

public class SessionProvider {

    private final SessionManager mSessionManager;

    public SessionProvider(Context context) {
        SessionDataStore sessionDataStore = new SessionDataStore(context);
        mSessionManager = new SessionManager(sessionDataStore);
    }

    public SessionManager provideSessionManager() {
       return mSessionManager;
    }
}
