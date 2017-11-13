package com.interview.producer;

import com.interview.model.RTPhoneNumber;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by avoiculet on 12/11/2017.
 */
public class PhoneNumberGeneratorTest {

    private ManchesterUKPhoneNumberGenerator phoneNumberGenerator;

    @Before
    public void setUp() {
        this.phoneNumberGenerator = new ManchesterUKPhoneNumberGenerator();
    }
    @Test
    public void testPhoneNumberGeneratorGeneratesAPhoneNumber() {

        RTPhoneNumber phoneNumber = phoneNumberGenerator.generate();
        assertTrue(phoneNumber != null);
    }

    @Test
    public void testPhoneNumberGeneratorDoesNotGenerateTheSameNumbersConsecutively() {
        RTPhoneNumber phoneNumber1 = phoneNumberGenerator.generate();
        RTPhoneNumber phoneNumber2 = phoneNumberGenerator.generate();
        assertFalse(phoneNumber1.getTelephoneNumber().equals(phoneNumber2.getTelephoneNumber()));
    }

}
