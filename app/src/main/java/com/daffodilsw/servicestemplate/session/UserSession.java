package com.daffodilsw.servicestemplate.session;

import com.libpersistance.Session;

public class UserSession implements Session {

    @Override
    public String getAuthToken() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
