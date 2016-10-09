package com.libpersistance;

public class SessionManager {

    private final DataStore<Session> mDataStore;

    public SessionManager(DataStore<Session> dataStore) {
        mDataStore = dataStore;
    }

    public void storeSession(Session session) {
        mDataStore.createOrUpdateData(session);
    }

    public Session readSession() {
        return mDataStore.retrieveData();
    }

    public void clearSession() {
        mDataStore.deleteData();
    }

}
