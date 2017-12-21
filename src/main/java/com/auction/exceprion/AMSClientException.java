package com.auction.exceprion;

/**
 * Created by Helga on 03.12.2017.
 */
public class AMSClientException extends Exception {

    public AMSClientException(final String message) {
        super(message);
    }

    public AMSClientException(final String message, Throwable cause) {
        super(message, cause);
    }
}
