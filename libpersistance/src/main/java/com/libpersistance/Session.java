package com.libpersistance;

public interface Session {

    String getAuthToken();

    boolean isValid();

}
