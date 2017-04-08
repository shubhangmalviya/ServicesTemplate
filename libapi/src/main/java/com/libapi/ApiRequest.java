package com.libapi;

import retrofit2.Call;

/**
 * Implementation for a single api request.
 * It has features to cancel and check whether api call is in progress.
 *
 * @param <REQUEST> the type of request object.
 * @param <RESPONSE> the type of response object.
 * @param <SERVICE> the type of service to be created.
 */
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
     * This is required in order to create the instance of the service class.
     *
     * @return the service interface class.
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
     * Prepares the error lookup messages for this particular API Request.
     *
     * @return the error look up table.
     */
    protected abstract ErrorLookupTable prepareErrorLookupMessages();

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
