package com.interview.reader;

import com.interview.model.PhoneNumberMessageImpl;
import com.interview.model.RTPhoneNumber;
import com.interview.model.UKPhoneNumber;
import org.springframework.amqp.core.Message;

/**
 * Created by avoiculet on 13/11/2017.
 */
public interface PhoneNumberReceiver {
    public void receiveMessage(PhoneNumberMessageImpl message);
}
