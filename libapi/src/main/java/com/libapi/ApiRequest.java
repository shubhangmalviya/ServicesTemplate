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
public abstract class ApiRequest<REQUEST, RESPONSE> {

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
     * Makes the actual API request.
     *
     * @param request the request object.
     * @param serviceCreator the implementation of {@link ServiceCreator} class.
     * @return the API call instance.
     */
    protected abstract Call<RESPONSE> makeRequest(REQUEST request, ServiceCreator serviceCreator);

    /**
     * Invokes an API request on the cloud.
     *
     * @param request the request object.
     * @param responseCallback the response callback.
     */
    public void makeRequest(REQUEST request, ResponseCallback<RESPONSE> responseCallback) {
        mApiCall = makeRequest(request, mServiceCreator);
        mApiCall.enqueue(new ResponseWrapper<>(mErrorResponseTransformer, responseCallback));
    }

    /**
     * Cancels any ongoing API call.
     */
    public void cancel() {
        APIUtils.cancelAPIRequest(mApiCall);
        if (APIUtils.shouldDisposeAPICall(mApiCall)) {
            mApiCall = null;
        }
    }

    /**
     * Checks whether an API call is in progress.
     *
     * @return true if an API call is in progress, false otherwise.
     */
    public  boolean isInProgress() {
        return APIUtils.isAPICallInProgress(mApiCall);
    }
}