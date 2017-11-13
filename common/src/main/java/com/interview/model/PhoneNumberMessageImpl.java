package com.interview.model;

import java.util.UUID;

/**
 * Created by avoiculet on 13/11/2017.
 */
public class PhoneNumberMessageImpl implements PhoneNumberMessage{
    private String phoneNumber;
    private String id;

    public PhoneNumberMessageImpl(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID().toString();
    }

    public PhoneNumberMessageImpl(String phoneNumber, String id) {
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public PhoneNumberMessageImpl() {

    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "id: " + id + "; phoneNumer: " + phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
