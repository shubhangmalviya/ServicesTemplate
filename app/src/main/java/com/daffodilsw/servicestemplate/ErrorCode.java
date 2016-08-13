package com.daffodilsw.servicestemplate;

/**
 * A representation and collection of all known Error codes returned from the API.
 */
public enum ErrorCode {
    E_400,
    E_401,
    E_403,
    E_404,
    E_409,
    E_451,
    E_500,
    E_501,
    E_999,
    E_415;

    /**
     * Gets the error code if one is set, otherwise null is returned.
     *
     * @param code the error code as integer.
     * @return the error code object or null.
     */
    public static ErrorCode getErrorCode(int code) {
        String enumErrorCode = "E_" + code;
        ErrorCode errorCode = null;
        try {
            errorCode = Enum.valueOf(ErrorCode.class, enumErrorCode);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return errorCode;
    }
}
