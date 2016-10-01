package com.libapi;

/**
 * The Response callback invoked after the result is received and analysed.
 *
 * @param <T> the type of the response.
 */
public interface ResponseCallback<T> {

    /**
     * Invoked when we have the success result in the defined format from the APIs.
     *
     * @param data  the success response data
     */
    void onSuccess(T data);

    /**
     * Invoked when we encounter any error from the APIs.
     *
     * @param errorResponse the error response with proper display message
     *                      and proper information for debugging.
     */
    void onFailure(ErrorResponse errorResponse);

}
