package com.libapi;

/**
 * Represents an API error response. This includes the human readable error messages and
 * also includes the proper meta info that could be helpful during the debugging.
 */
public class ErrorResponse {

    public static final int INVALID_STATUS_CODE = 1001;

    private int mStatusCode;
    private String mErrorResponsePayload;

    private Enum mErrorCode;
    private int mErrorMessage;
    private Throwable mThrowable;

    private ErrorResponse(Builder builder) {
        mStatusCode = builder.mStatusCode;
        mErrorResponsePayload = builder.mErrorResponsePayload;
        mErrorCode = builder.mErrorCode;
        mErrorMessage = builder.mErrorMessage;
        mThrowable = builder.mThrowable;
    }

    /**
     * Gets the HTTP status code.
     * @return valid HTTP status code or {@link ErrorResponse#INVALID_STATUS_CODE}
     */
    public int getStatusCode() {
        return mStatusCode;
    }

    /**
     * Gets the error payload returned from the server.
     * @return error payload as String.
     */
    public String getErrorResponsePayload() {
        return mErrorResponsePayload;
    }

    /**
     * Get the error code associated with this API request.
     * @return the API error code enumeration.
     */
    public Enum getErrorCode() {
        return mErrorCode;
    }

    /**
     * Gets the human readable error message for this request.
     * @return the String resource of the error message.
     */
    public int getErrorMessage() {
        return mErrorMessage;
    }

    /**
     * Gets the associated exception or error object with this request if any.
     * @return the instance of error or exception.
     */
    public Throwable getThrowable() {
        return mThrowable;
    }


    /**
     * Builds the Error response.
     */
    public static final class Builder {
        private int mStatusCode = INVALID_STATUS_CODE;
        private String mErrorResponsePayload;
        private Enum mErrorCode;
        private int mErrorMessage;
        private Throwable mThrowable;

        public Builder() {
        }

        public Builder(ErrorResponse copy) {
            this.mStatusCode = copy.mStatusCode;
            this.mErrorResponsePayload = copy.mErrorResponsePayload;
            this.mErrorCode = copy.mErrorCode;
            this.mErrorMessage = copy.mErrorMessage;
            this.mThrowable = copy.mThrowable;
        }

        /**
         * Set the HTTP status code.
         * @param statusCode the HTTP status code.
         */
        public Builder withStatusCode(int statusCode) {
            mStatusCode = statusCode;
            return this;
        }

        /**
         * Sets the error response payload returned from the server.
         * @param errorResponsePayload the error response payload.
         */
        public Builder withErrorResponsePayload(String errorResponsePayload) {
            mErrorResponsePayload = errorResponsePayload;
            return this;
        }

        /**
         * Sets the error code associated with this request.
         * @param errorCode the API error code enumeration.
         */
        public Builder withErrorCode(Enum errorCode) {
            mErrorCode = errorCode;
            return this;
        }

        /**
         * Sets the human readable error message for this request.
         * @param errorMessage the String resource of the error message.
         */
        public Builder withErrorMessage(int errorMessage) {
            mErrorMessage = errorMessage;
            return this;
        }

        /**
         * Sets the associated exception or error object with this request if any.
         * @param throwable the instance of error or exception.
         */
        public Builder withThrowable(Throwable throwable) {
            mThrowable = throwable;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
