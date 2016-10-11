package com.libservices.config;

import com.libapi.ErrorLookupTable;
import com.libapi.ErrorResponse;
import com.libapi.ErrorResponseTransformer;

/**
 * Translates the error response according to the app business logic.
 */
public class AppErrorResponseTransformer extends ErrorResponseTransformer {

    /**
     * Creates the instance.
     *
     * @param errorLookupTable the error message resolver used to look up.
     */
    public AppErrorResponseTransformer(ErrorLookupTable errorLookupTable) {
        super(errorLookupTable);
    }

    @Override
    public ErrorResponse transform(int httpStatusCode, String errorResponsePayload) {

        return new ErrorResponse.Builder()
                .withStatusCode(httpStatusCode)
                .build();
    }
}
