package com.libpreferences;

public interface Session {

    String getAuthToken();

    boolean isValid();

}
