package com.libapi;

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

    private final Notifier<T> mNotifier;
    private final ErrorPayloadHandler mErrorPayloadHandler;

    /**
     *
     * Creates an instance.
     *
     * @param responseCallback the error response callback.
     * @param errorResponseTransformer an error transformer instance that
     *                                 could mutate the error mappings.
     */
    public ResponseWrapper(ErrorResponseTransformer errorResponseTransformer,
                           ResponseCallback<T> responseCallback) {
        ErrorLookupTable errorLookupTable = errorResponseTransformer.getErrorLookupTable();
        mNotifier = new Notifier<>(responseCallback, errorLookupTable);
        mErrorPayloadHandler = new ErrorPayloadHandler(errorResponseTransformer, mNotifier);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.isSuccessful()) {
            handleSuccessResponse(response);
            return;
        }

        handleErrorResponse(response);
    }

    private void handleSuccessResponse(Response<T> response) {
        // if the response is successful then simply forward the success response to the callbacks.
        mNotifier.notifyWithSuccess(response.body());
    }

    private void handleErrorResponse(Response<T> response) {
        mErrorPayloadHandler.handleErrorPayload(response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {

        // Handle the failure gracefully.
        if (throwable instanceof ConnectException
                || throwable instanceof UnknownHostException) {
            // Network error
            mNotifier.notifyConnectivityError(throwable);
        } else {
            // some more complex error occurred like conversion etc.
            mNotifier.notifyUnknownError(throwable);
        }

    }
}
