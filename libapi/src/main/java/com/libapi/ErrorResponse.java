package com.libapi;

public class ErrorResponse {

    public static final int INVALID_STATUS_CODE = 1001;

    private final int mStatusCode;
    private final String mErrorResponsePayload;

    private int mErrorCode;
    private int mErrorMessage;
    private Throwable mThrowable;

    public ErrorResponse(int statusCode, String errorResponsePayload) {
        mStatusCode = statusCode;
        mErrorResponsePayload = errorResponsePayload;
    }

    public ErrorResponse(int errorMessage) {
        mErrorMessage = errorMessage;
        mStatusCode = INVALID_STATUS_CODE;
        mErrorResponsePayload = null;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public void setThrowable(Throwable throwable) {
        mThrowable = throwable;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public int getErrorMessage() {
        return mErrorMessage;
    }

    public String getErrorResponsePayload() {
        return mErrorResponsePayload;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
