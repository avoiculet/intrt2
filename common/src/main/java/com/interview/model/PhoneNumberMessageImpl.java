package com.interview.model;

import java.util.UUID;

/**
 * Created by avoiculet on 13/11/2017.
 */
public class PhoneNumberMessageImpl implements PhoneNumberMessage{
    private String telephoneNumber;
    private String id;

    public PhoneNumberMessageImpl(String phoneNumber) {
        this.telephoneNumber = phoneNumber;
        this.id = UUID.randomUUID().toString();
    }

    public PhoneNumberMessageImpl(String phoneNumber, String id) {
        this.telephoneNumber = phoneNumber;
        this.id = id;
    }

    public PhoneNumberMessageImpl() {

    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public String toString() {
        return "id: " + id + "; telephoneNumber: " + telephoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
