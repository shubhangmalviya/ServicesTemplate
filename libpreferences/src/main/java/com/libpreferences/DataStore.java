package com.libpreferences;

public interface DataStore<T> {

    void createOrUpdateData(T data);

    T retrieveData();

    void deleteData();

}
