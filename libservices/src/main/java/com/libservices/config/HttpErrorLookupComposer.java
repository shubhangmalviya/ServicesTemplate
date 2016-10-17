package com.libservices.config;

import com.libapi.ErrorLookupTable;
import com.libservices.R;

public class HttpErrorLookupComposer {

    public void addStandardErrorLookup(ErrorLookupTable errorLookupTable) {
        errorLookupTable.translate(ErrorCodes.E_500, R.string.internal_server_error);
    }

}
