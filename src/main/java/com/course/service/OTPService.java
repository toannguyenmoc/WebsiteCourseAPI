package com.course.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class OTPService {
	private final Map<String, String> otpCache = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Tạo và lưu OTP
    public String generateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpCache.put(email, otp);

        // Xóa OTP sau 60s
        scheduler.schedule(() -> otpCache.remove(email), 60, TimeUnit.SECONDS);
        return otp;
    }

    // Kiểm tra OTP
    public boolean validateOtp(String email, String otp) {
        String savedOtp = otpCache.get(email);
        return savedOtp != null && savedOtp.equals(otp);
    }

    // Xóa OTP thủ công (sau khi xác nhận)
    public void clearOtp(String email) {
        otpCache.remove(email);
    }
}
