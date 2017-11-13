package com.interview.reader;

import com.interview.model.PhoneNumberMessageImpl;

/**
 * Created by avoiculet on 13/11/2017.
 */
public interface PhoneNumberReceiver {
    public void receiveMessage(PhoneNumberMessageImpl message);
}
