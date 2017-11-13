package com.interview.model;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.interview.util.PhoneNumberException;

/**
 * Created by avoiculet on 12/11/2017.
 */
public class IdPhoneNumber implements RTPhoneNumber {
    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    private final String phoneNumber;

    private final Phonenumber.PhoneNumber parsedPhoneNumber;
    private final String id;

    public IdPhoneNumber(String id, String phoneNumber) {
        this.parsedPhoneNumber = parsePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    private Phonenumber.PhoneNumber parsePhoneNumber(String phoneNumber) {
        if (!phoneNumber.startsWith("+")) {
            throw new PhoneNumberException("Invalid phone number: It does not start with a +");
        }

        try {
            return phoneUtil.parse(phoneNumber, "ZZ");
        } catch (NumberParseException e) {
            throw new PhoneNumberException(e);
        }
    }

    @Override
    public String getNumber() {
        return phoneNumber;
    }

    @Override
    public int getCountryCode() {
        return parsedPhoneNumber.getCountryCode();
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

    @Override
    public String getId() {
        return id;
    }
}
