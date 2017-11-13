package com.interview.util;

import com.google.i18n.phonenumbers.NumberParseException;

/**
 * Created by avoiculet on 12/11/2017.
 */
public class PhoneNumberException extends RuntimeException {
    public PhoneNumberException(String message) {
        super(message);
    }

    public PhoneNumberException(NumberParseException e) {
        super(e);
    }
}
