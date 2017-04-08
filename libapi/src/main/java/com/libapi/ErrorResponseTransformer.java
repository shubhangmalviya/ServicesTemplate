package com.libapi;

/**
 * Provides a mechanism to the user for mutating the error response received.
 * So as to customise the error received.
 */
public abstract class ErrorResponseTransformer {

    private ErrorLookupTable mErrorLookupTable;

    /**
     * Creates the instance.
     * @param errorLookupTable the error message resolver used to look up.
     */
    public ErrorResponseTransformer(ErrorLookupTable errorLookupTable) {
        mErrorLookupTable = errorLookupTable;
    }

    /**
     * Creates the instance.
     */
    public ErrorResponseTransformer() {
        mErrorLookupTable = new ErrorLookupTable();
    }

    /**
     * Provides an easy way to intercept and mutate the Error response delivered.
     *
     * @param httpStatusCode the HTTP status code.
     * @param errorResponsePayload the error response payload received.
     */
    public abstract ErrorResponse transform(int httpStatusCode, String errorResponsePayload);

    /**
     * Sets the error message resolver to resolve errors.
     * @param errorLookupTable the error message resolver used to look up.
     */
    public void setErrorLookupTable(ErrorLookupTable errorLookupTable) {
        mErrorLookupTable = errorLookupTable;
    }

    /**
     * Gets the error message resolver associated with this instance.
     * @return the instance of error message resolver.
     */
    public ErrorLookupTable getErrorLookupTable() {
        return mErrorLookupTable;
    }
}
