package com.libapi;

/**
 * An implementation of the web services instance creator.
 */
public interface ServiceCreator {

    /**
     * Creates the services for a given HTTP Url, useful when testing
     * through multiple endpoints and unit testing
     *
     * @param <T>   type of the service.
     * @param clazz the service class.
     * @return the created services implementation.
     */
    <T> T createService(Class<T> clazz);

}
