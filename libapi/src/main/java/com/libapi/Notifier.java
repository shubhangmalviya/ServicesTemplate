package com.libapi;

import static com.libapi.ApplicationErrorCodes.INTERNET_ERROR;
import static com.libapi.ApplicationErrorCodes.UNKNOWN_ERROR;

class Notifier<T> {

    private final ResponseCallback<T> mResponseCallback;
    private final ErrorLookupTable mErrorLookupTable;

    Notifier(ResponseCallback<T> responseCallback, ErrorLookupTable errorLookupTable) {
        mResponseCallback = responseCallback;
        mErrorLookupTable = errorLookupTable;
    }

    private ErrorResponse createErrorResponse(Throwable throwable, int errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(errorMessage);
        errorResponse.setThrowable(throwable);
        return errorResponse;
    }

    void notifyUnknownError(Throwable throwable) {
        int errorMessage = mErrorLookupTable.resolve(UNKNOWN_ERROR);
        ErrorResponse errorResponse = createErrorResponse(throwable, errorMessage);
        notifyWithFailure(errorResponse);
    }

    void notifyConnectivityError(Throwable throwable) {
        int errorMessage = mErrorLookupTable.resolve(INTERNET_ERROR);
        ErrorResponse errorResponse = createErrorResponse(throwable, errorMessage);
        notifyWithFailure(errorResponse);
    }

    void notifyWithSuccess(T responseObject) {
        if (mResponseCallback != null) {
            mResponseCallback.onSuccess(responseObject);
        }
    }

    void notifyWithFailure(ErrorResponse errorResponse) {
        if (mResponseCallback != null) {
            mResponseCallback.onFailure(errorResponse);
        }
    }
}
