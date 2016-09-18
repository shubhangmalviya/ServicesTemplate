package com.libapi;

/**
 * Provides a mechanism to the user for mutating the error response received.
 * So as to customise the error received.
 */
public interface ErrorResponseTransformer {

    /**
     * Provides an easy way to intercept and mutate the Error response delivered.
     *
     * @param errorResponsePayload the error response payload received.
     * @param errorResponse the error response constructed.
     * @param errorMessageResolver the error message resolver.
     */
    void transform(String errorResponsePayload, ErrorResponse errorResponse, ErrorMessageResolver errorMessageResolver);
}
