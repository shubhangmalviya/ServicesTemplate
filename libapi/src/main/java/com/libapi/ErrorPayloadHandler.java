package com.libapi;

import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

/**
 * Class that handles the error payload and converts them into the appropriate error response.
 */
class ErrorPayloadHandler {

    private final ErrorResponseTransformer mErrorResponseTransformer;
    private final Notifier<?> mNotifier;

    /**
     * Creates an instance.
     *
     * @param errorResponseTransformer transformer implementation for converting payload to Error Response.
     * @param notifier for notifying in case of success or failure.
     */
    ErrorPayloadHandler(ErrorResponseTransformer errorResponseTransformer, Notifier<?> notifier) {
        mErrorResponseTransformer = errorResponseTransformer;
        mNotifier = notifier;
    }

    /**
     * Creates an instance after parsing and extracting the error payload.
     *
     * @param errorResponseBody the response body.
     * @param httpStatusCode the HTTP status code.
     * @return appropriate ErrorResponse instance.
     * @throws IOException in case the error response payload is not convertible to String.
     */
    private ErrorResponse createErrorResponse(ResponseBody errorResponseBody, int httpStatusCode) throws IOException {

        String errorBodyPayload;

        // ... in case of error response we need to extract the error payload.
        errorBodyPayload = errorResponseBody.string();

        ErrorResponse errorResponse;

        if (mErrorResponseTransformer != null) {
            errorResponse = mErrorResponseTransformer.transform(httpStatusCode, errorBodyPayload);
        }else {
            errorResponse = new ErrorResponse.Builder()
                    .withStatusCode(httpStatusCode)
                    .withErrorResponsePayload(errorBodyPayload)
                    .build();
        }

        return errorResponse;
    }

    /**
     * Handles the error payload.
     * @param response the error response.
     */
    void handleErrorPayload(Response<?> response) {
        try {
            // ... in case of error response we need to extract the error payload.
            ErrorResponse errorResponse
                    = createErrorResponse(response.errorBody(), response.code());

            // notify the error response callbacks.
            mNotifier.notifyWithFailure(errorResponse);

        } catch (IOException e) {
            e.printStackTrace();
            mNotifier.notifyUnknownError(e);
        }
    }
}
