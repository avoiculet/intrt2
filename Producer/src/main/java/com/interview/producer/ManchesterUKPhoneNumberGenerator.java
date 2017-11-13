package com.interview.producer;

import com.interview.model.IdPhoneNumber;
import com.interview.model.RTPhoneNumber;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * Created by avoiculet on 12/11/2017.
 */
@Component
public class ManchesterUKPhoneNumberGenerator implements PhoneNumberGenerator {

    private static String COUNTRY_CODE = "+44";
    private static String MCR_CODE = "161";
    private static int TEN_MILLION = 10000000;

    @Override
    public RTPhoneNumber generate() {
        StringBuilder phoneNumber = new StringBuilder(COUNTRY_CODE).append(MCR_CODE);
        Random random = new Random(System.nanoTime());
        phoneNumber.append(random.nextInt(TEN_MILLION));
        return new IdPhoneNumber(UUID.randomUUID().toString(), phoneNumber.toString());
    }
}
