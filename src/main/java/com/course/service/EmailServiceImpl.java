package com.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void sendWelcomeEmail(String to) {
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	        helper.setTo(to);
	        helper.setSubject("Chào mừng bạn đến với website học online DevCourse!");

	        String content = """
	            <p>Xin chào bạn,</p>
	            <p>Chào mừng bạn đã gia nhập cộng đồng <strong>DevCourse</strong>!</p>
	            <ul>
	                <li>✅ Truy cập khoá học và tài nguyên chất lượng</li>
	                <li>✅ Cập nhật hồ sơ cá nhân, theo dõi công việc mơ ước</li>
	                <li>✅ Nhận hỗ trợ tận tâm từ đội ngũ chúng tôi</li>
	            </ul>
	            <p>👉 <a href='http://localhost:5173/login'>Bắt đầu ngay</a></p>
	            <br>
	            <p>Trân trọng,<br><strong>Đội ngũ DevCourse</strong></p>
	        """;

	        helper.setText(content, true); 
	        mailSender.send(message);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void sendOTP(String to, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Mã OTP đặt lại mật khẩu");
        message.setText("Mã OTP của bạn là: " + otp + "\nHiệu lực trong 60 giây.");
        mailSender.send(message);
		
	}

}
