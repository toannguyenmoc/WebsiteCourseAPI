package com.course.service;

public interface EmailService {
	void sendWelcomeEmail(String to);
	public void sendOTP(String to, String otp);
}
