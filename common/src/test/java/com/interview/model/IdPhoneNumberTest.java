package com.interview.model;

import com.interview.util.PhoneNumberException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by avoiculet on 12/11/2017.
 */
public class IdPhoneNumberTest {
    @Test(expected= PhoneNumberException.class)
    public void testIdPhoneNumberCannotBeCreatedWithoutPlus() {
        String badNumber = "00441619109020";
        new IdPhoneNumber("12121@1", badNumber);
    }

    @Test(expected = PhoneNumberException.class)
    public void testBadNumberIsRejectedOnObjectCreation() {
        String badNumber = "+23zfg22121";
        new IdPhoneNumber("1212122222" , badNumber);
    }

    @Test
    public void testCorrectNumberReturnsCorrectCountryCode() {
        String phoneNumber=  "+447500756789";
        assertTrue(new IdPhoneNumber("1212122222", phoneNumber).getCountryCode() == 44);
    }
}
