package com.libapi;

import retrofit2.Call;

/**
 * Implementation for a single api request.
 * It has features to cancel and check whether api call is in progress.
 *
 * @param <REQUEST> the type of request object.
 * @param <RESPONSE> the type of response object.
 */
// Future we have to prepare error response message.
public abstract class ApiRequest<REQUEST, RESPONSE, SERVICE> {

    private final ErrorResponseTransformer mErrorResponseTransformer;
    private final ServiceCreator mServiceCreator;
    private Call<RESPONSE> mApiCall;

    /**
     * Creates an instance of the api request.
     *
     * @param errorResponseTransformer transformer useful in case of resolving the application error.
     * @param serviceCreator the configured service manager for creating the services instances.
     */
    public ApiRequest(ErrorResponseTransformer errorResponseTransformer,
                      ServiceCreator serviceCreator) {
        mErrorResponseTransformer = errorResponseTransformer;
        mServiceCreator = serviceCreator;
    }

    /**
     * Creates an instance of the service interface class.
     *
     * @return the instance of class.
     */
    protected abstract Class<SERVICE> getServiceClass();

    /**
     * Makes the actual API request.
     *
     * @param request the request object.
     * @param service the service class which could make request.
     * @return the API call instance.
     */
    protected abstract Call<RESPONSE> makeRequest(REQUEST request, SERVICE service);

    /**
     * Invokes an API request on the cloud.
     *
     * @param request the request object.
     * @param responseCallback the response callback.
     */
    public void makeRequest(REQUEST request, ResponseCallback<RESPONSE> responseCallback) {
        SERVICE service = mServiceCreator.createService(getServiceClass());
        mApiCall = makeRequest(request, service);
        mApiCall.enqueue(new ResponseWrapper<>(mErrorResponseTransformer, responseCallback));
    }

    /**
     * Cancels any ongoing API call. It cancels only when api call is enqueued
     * or is being executed and not is already cancelled.
     */
    public void cancel() {
        if (mApiCall != null && mApiCall.isExecuted() && !mApiCall.isCanceled()) {
            mApiCall.cancel();
            mApiCall = null;
        }
    }
}
