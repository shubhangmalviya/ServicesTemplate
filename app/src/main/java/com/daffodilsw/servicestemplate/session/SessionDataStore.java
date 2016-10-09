package com.daffodilsw.servicestemplate.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;
import com.libpersistance.DataStore;
import com.libpersistance.Session;

import java.io.IOException;

public class SessionDataStore implements DataStore<Session> {

    private static final String USER_SESSION_FILE = "user_prefs";
    private static final String USER_SESSION_KEY = "user";
    private final SharedPreferences mSharedPreferences;

    public SessionDataStore(Context context) {
        mSharedPreferences = context.getSharedPreferences(USER_SESSION_FILE, Context.MODE_PRIVATE);
    }

    @Override
    public void createOrUpdateData(Session data) {
        try {
            String user = LoganSquare.serialize(data);
            mSharedPreferences.edit().putString(USER_SESSION_KEY, user).apply();
        } catch (IOException e) {
            Log.e("Session Data Store", "cannot save session", e);
        }
    }

    @Override
    public Session retrieveData() {
        return null;
    }

    @Override
    public void deleteData() {

    }
}
