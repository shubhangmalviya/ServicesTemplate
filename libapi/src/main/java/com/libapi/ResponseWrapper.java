package com.libapi;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A wrapper layer over the retrofit callback, written for distinguishing the success
 * and failure responses.
 *
 * @param <T> the class type of the success response expected.
 */
public class ResponseWrapper<T> implements Callback<T> {

    private final ErrorMessageResolver mErrorMessageResolver;
    private final ResponseCallback<T> mResponseCallback;
    private final ErrorResponseTransformer mErrorResponseTransformer;

    /**
     *
     * Creates an instance.
     *
     * @param errorMessageResolver the error message resolver.
     * @param responseCallback the error response callback.
     * @param errorResponseTransformer an error transformer instance that could mutate the error mappings.
     */
    public ResponseWrapper(ErrorMessageResolver errorMessageResolver,
                           ResponseCallback<T> responseCallback,
                           ErrorResponseTransformer errorResponseTransformer) {
        mErrorMessageResolver = errorMessageResolver;
        mResponseCallback = responseCallback;
        mErrorResponseTransformer = errorResponseTransformer;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        // if the response is successful then simply forward the success response to the callbacks.
        if (response.isSuccessful()) {
            mResponseCallback.onSuccess(response.body());
            return;
        }

        String errorBodyPayload;

        try {

            // ... in case of error response we need to extract the error payload.
            errorBodyPayload = response.errorBody().string();
            // read the status code.
            int httpStatusCode = response.code();

            // create error response.
            ErrorResponse errorResponse = new ErrorResponse(httpStatusCode, errorBodyPayload);

            if (mErrorResponseTransformer != null) {
                mErrorResponseTransformer.transform(errorBodyPayload, errorResponse, mErrorMessageResolver);
            }

            // notify the error response callbacks.
            mResponseCallback.onFailure(errorResponse);

        } catch (IOException e) {
            e.printStackTrace();
            ErrorResponse errorResponse = new ErrorResponse(R.string.some_error_occurred);
            errorResponse.setThrowable(e);
            mResponseCallback.onFailure(errorResponse);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {

        // Handle the failure gracefully.
        ErrorResponse errorResponse;

        if (throwable instanceof ConnectException
                || throwable instanceof UnknownHostException) {
            // Network error
            errorResponse = new ErrorResponse(R.string.internet_error);
        } else {
            // some more complex error occurred like conversion etc.
            errorResponse = new ErrorResponse(R.string.some_error_occurred);
        }

        //... set the error in the response for debugging.
        errorResponse.setThrowable(throwable);
        // notify the callers.
        mResponseCallback.onFailure(errorResponse);

    }
}
