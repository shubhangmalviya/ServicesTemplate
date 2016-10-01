package com.libapi;

public class ErrorResponse {

    public static final int INVALID_STATUS_CODE = 1001;

    private int mStatusCode = INVALID_STATUS_CODE;
    private String mErrorResponsePayload;

    private int mErrorCode;
    private int mErrorMessage;
    private Throwable mThrowable;

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    public String getErrorResponsePayload() {
        return mErrorResponsePayload;
    }

    public void setErrorResponsePayload(String errorResponsePayload) {
        mErrorResponsePayload = errorResponsePayload;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public int getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(int errorMessage) {
        mErrorMessage = errorMessage;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public void setThrowable(Throwable throwable) {
        mThrowable = throwable;
    }
}
