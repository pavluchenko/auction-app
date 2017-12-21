package com.auction.exceprion;

/**
 * Created by Helga on 03.12.2017.
 */
public class AMSServiceException extends Exception {
    public AMSServiceException(final String message) {
        super(message);
    }

    public AMSServiceException(final String message, Throwable cause) {
        super(message, cause);
    }
}
