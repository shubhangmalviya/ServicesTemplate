package com.libapi;

import retrofit2.Call;

import static com.libapi.ApiRequest.ErrorLookUpMergingStrategy.ACCEPT_LOCAL;
import static com.libapi.ApiRequest.ErrorLookUpMergingStrategy.ACCEPT_REMOTE;

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
    private final ErrorLookupMerger mErrorLookupMerger;
    private final ServiceCreator mServiceCreator;
    private ErrorLookUpMergingStrategy mErrorLookUpMergingStrategy = ACCEPT_REMOTE;
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
        mErrorLookupMerger = new ErrorLookupMerger();
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
     * Sets the strategy for error lookup, in case of a lookup existing in the API request class
     * and the lookup coming from the Business layer, which one to choose.
     * The Default is {@link ErrorLookUpMergingStrategy#ACCEPT_REMOTE}
     * @param errorLookUpMergingStrategy Strategy either one of {@link ErrorLookUpMergingStrategy#ACCEPT_REMOTE}
     *                                   or {@link ErrorLookUpMergingStrategy#ACCEPT_LOCAL}
     */
    public void setErrorLookUpMergingStrategy(ErrorLookUpMergingStrategy errorLookUpMergingStrategy) {
        mErrorLookUpMergingStrategy = errorLookUpMergingStrategy;
    }

    /**
     * Invokes an API request on the cloud.
     *
     * @param request the request object.
     * @param responseCallback the response callback.
     */
    public void makeRequest(REQUEST request, ResponseCallback<RESPONSE> responseCallback) {
        SERVICE service = mServiceCreator.createService(getServiceClass());
        mergeRemoteAndLocalErrorLookup();
        mApiCall = makeRequest(request, service);
        mApiCall.enqueue(new ResponseWrapper<>(mErrorResponseTransformer, responseCallback));
    }

    /**
     * Merges the error lookup table with outside high priority.
     */
    private void mergeRemoteAndLocalErrorLookup() {
        ErrorLookupTable locallyCreated = prepareErrorLookupMessages();
        ErrorLookupTable remotelyProvided = mErrorResponseTransformer.getErrorLookupTable();

        ErrorLookupTable errorLookupTable = mErrorLookUpMergingStrategy == ACCEPT_LOCAL ?
                acceptLocalStrategy(locallyCreated, remotelyProvided) :
               acceptRemoteStrategy(locallyCreated, remotelyProvided) ;

        mErrorResponseTransformer.setErrorLookupTable(errorLookupTable);
    }

    private ErrorLookupTable acceptLocalStrategy(ErrorLookupTable locallyCreated, ErrorLookupTable remotelyProvided) {

        return mErrorLookupMerger.mergeErrorMessages(locallyCreated, remotelyProvided,
        ErrorLookupMerger.Strategy.ACCEPT_LEFT);
    }

    private ErrorLookupTable acceptRemoteStrategy(ErrorLookupTable locallyCreated, ErrorLookupTable remotelyProvided) {
        return mErrorLookupMerger.mergeErrorMessages(locallyCreated, remotelyProvided,
                ErrorLookupMerger.Strategy.ACCEPT_RIGHT);
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

    /**
     * Strategy for merging the error lookup values either coming from the API layer and the BL layer
     */
    public enum ErrorLookUpMergingStrategy {
        /**
         * The remote strategy would accept the values from the BL layer.
         */
        ACCEPT_REMOTE,
        /**
         * The local strategy would accept the values from the API layer.
         */
        ACCEPT_LOCAL
    }
}
