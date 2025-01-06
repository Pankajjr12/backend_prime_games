package com.kumar.gamesstore.utils;



import java.util.concurrent.ThreadLocalRandom;

public class OtpUtils {
    public static String generateOtp() {
        int otpLength = 6;
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(ThreadLocalRandom.current().nextInt(10));  // Thread-safe random
        }
        return otp.toString();
    }
}


