package com.libapi;

import java.util.HashMap;
import java.util.Map;

/**
 * A lookup table implementation for resolving and mapping the error codes
 * to their proper display Human Readable Error Messages.
 */
public class ErrorLookupTable {

    // Look up table.
    private final Map<Enum, Integer> mErrorCodeMap = new HashMap<>();

    /**
     * Stores a display message entry in the lookup table for a given error code.
     * @param errorCode the error code to be translated.
     * @param message the equivalent error message.
     */
    public void translate(Enum errorCode, int message) {
        mErrorCodeMap.put(errorCode, message);
    }

    /**
     * Checks whether the look up table contains the message entry for the given error code.
     * @param errorCode the error code.
     * @return true if the mapping exists false otherwise.
     */
    public boolean isResolvable(Enum errorCode) {
        return mErrorCodeMap.containsKey(errorCode);
    }

    /**
     * Gets the error map to give fine grained access to the Android Map APIs.
     *
     * @return the Map implementation.
     */
    protected Map<Enum, Integer> getErrorMap() {
        return mErrorCodeMap;
    }

    /**
     * Resolves the error code to the display message.
     * @param errorCode the error code.
     * @return the translated message equivalent of the error code.
     */
    public int resolve(Enum errorCode) {
        return isResolvable(errorCode) ? mErrorCodeMap.get(errorCode) : -1;
    }

}
