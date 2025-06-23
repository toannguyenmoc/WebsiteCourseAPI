package com.course.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OTPService {
	private final Map<String, String> otpStore = new HashMap<>();

    public String generateOTP(String email) {
        String otp = String.valueOf(new Random().nextInt(999999));
        otpStore.put(email, otp);
        return otp;
    }

    public boolean validateOTP(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }

    public void removeOTP(String email) {
        otpStore.remove(email);
    }
}
