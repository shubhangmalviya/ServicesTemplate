package com.daffodilsw.servicestemplate.session;

import com.libpersistance.Session;

public class UserSession implements Session {
    
    private String mAuthToken;
    private String mUserId;

    private UserSession(Builder builder) {
        mAuthToken = builder.mAuthToken;
        mUserId = builder.mUserId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserSession copy) {
        Builder builder = new Builder();
        builder.mAuthToken = copy.mAuthToken;
        builder.mUserId = copy.mUserId;
        return builder;
    }

    @Override
    public String getAuthToken() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }


    public static final class Builder {
        private String mAuthToken;
        private String mUserId;

        private Builder() {
        }

        public Builder withAuthToken(String val) {
            mAuthToken = val;
            return this;
        }

        public Builder withUserId(String val) {
            mUserId = val;
            return this;
        }

        public UserSession build() {
            return new UserSession(this);
        }
    }
}
