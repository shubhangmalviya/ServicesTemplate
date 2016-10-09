package com.libpersistance;

public interface DataStore<T> {

    void createOrUpdateData(T data);

    T retrieveData();

    void deleteData();

}
