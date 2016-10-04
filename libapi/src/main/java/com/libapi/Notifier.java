package com.libapi;

import static com.libapi.ApplicationErrorCodes.INTERNET_ERROR;
import static com.libapi.ApplicationErrorCodes.UNKNOWN_ERROR;

/**
 * Internal class for notifying the listeners.
 * @param <T> the Type of response body.
 */
class Notifier<T> {

    private final ResponseCallback<T> mResponseCallback;
    private final ErrorLookupTable mErrorLookupTable;

    /**
     * Creates the instance.
     * @param responseCallback the response callback.
     * @param errorLookupTable the error look up table.
     */
    Notifier(ResponseCallback<T> responseCallback, ErrorLookupTable errorLookupTable) {
        mResponseCallback = responseCallback;
        mErrorLookupTable = errorLookupTable;
    }

    /**
     * Internally creates an error response.
     * @param throwable the throwable instance useful for debugging.
     * @param errorMessage the human readable error message.
     * @return the fully created Error Response.
     */
    private ErrorResponse createErrorResponse(Throwable throwable, int errorMessage) {
        return new ErrorResponse.Builder()
                .withErrorMessage(errorMessage)
                .withThrowable(throwable)
                .build();
    }

    /**
     * Creates and notify the callers for an unknown error.
     * @param throwable the throwable instance.
     */
    void notifyUnknownError(Throwable throwable) {
        int errorMessage = mErrorLookupTable.resolve(UNKNOWN_ERROR);
        ErrorResponse errorResponse = createErrorResponse(throwable, errorMessage);
        notifyWithFailure(errorResponse);
    }

    /**
     * Creates and notify the callers in case of a connectivity errors.
     * @param throwable the throwable instance.
     */
    void notifyConnectivityError(Throwable throwable) {
        int errorMessage = mErrorLookupTable.resolve(INTERNET_ERROR);
        ErrorResponse errorResponse = createErrorResponse(throwable, errorMessage);
        notifyWithFailure(errorResponse);
    }

    /**
     * Notifies the listeners with the success object.
     * @param responseObject the success object.
     */
    void notifyWithSuccess(T responseObject) {
        if (mResponseCallback != null) {
            mResponseCallback.onSuccess(responseObject);
        }
    }

    /**
     * Notifies the listeners with the failure object.
     * @param errorResponse the error response with the failure object.
     */
    void notifyWithFailure(ErrorResponse errorResponse) {
        if (mResponseCallback != null) {
            mResponseCallback.onFailure(errorResponse);
        }
    }
}
