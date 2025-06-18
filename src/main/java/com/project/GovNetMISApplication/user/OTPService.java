package com.project.GovNetMISApplication.user;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OTPService {
    private final UsersRepository userRepository; // Your user repository
    private final JavaMailSender javaMailSender;
    public OTPService(UsersRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    public String generateOTP() {
        return String.format("%06d", new Random().nextInt(1000000));
    }


    // ... (Other methods)

    public void storeOTPCode(Users user, String otpCode) {
        // Set the OTP code and expiration time for the user
        user.setOtpCode(otpCode);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(3)); // Example: OTP valid for 3 minutes
        userRepository.save(user);
    }

    public boolean isOTPValid(Users user, String otpCode) {
        // Check if the provided OTP code matches the user's OTP code and is not expired
        return user.getOtpCode() != null
                && user.getOtpExpiration() != null
                && user.getOtpExpiration().isAfter(LocalDateTime.now());
    }
    public void sendOTP(String recipientEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("aman39469@gmail.com");
        message.setTo("ezatullah77855@gmail.com");
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("OPT Sent successFully");

    }

}
