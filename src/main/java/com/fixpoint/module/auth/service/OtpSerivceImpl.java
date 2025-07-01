package com.fixpoint.module.auth.service;

import com.fixpoint.common.service.EmailService;
import com.fixpoint.module.auth.entities.Otp;
import com.fixpoint.module.auth.repositories.OtpRepository;
import com.fixpoint.module.user.entity.User;
import com.fixpoint.module.user.repository.UserRepository;
import com.fixpoint.utils.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpSerivceImpl implements OtpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public boolean generateOtp(String email) {
        boolean isExist = userRepository.existsByEmail(email);
        System.out.print(isExist);
        if(isExist){
            Optional<User> byEmailId = userRepository.findByEmail(email);
            User user = byEmailId.get();
            String userName=user.getUsername();
            String generatedOtp = OtpUtil.generateOtp(6);
            Otp otp = Otp.builder()
                    .email(email)
                    .otp(generatedOtp)
                    .build();
            Otp save = otpRepository.save(otp);
            String body = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<body style=\"font-family: Arial, sans-serif; color: #333;\">\n"
                    + "  <p>Hi {{UserName}},</p>\n"
                    + "  <p>Your one-time password (OTP) is:</p>\n"
                    + "  <h2 style=\"color: #2E86C1;\">{{OTP}}</h2>\n"
                    + "  <p>This code is valid for the next <strong>5 minutes</strong>. Please do not share it with anyone.</p>\n"
                    + "  <p>If you did not request this, you can safely ignore this email.</p>\n"
                    + "  <br>\n"
                    + "  <p>Best regards,<br><strong>Team {{YourAppName}}</strong></p>\n"
                    + "</body>\n"
                    + "</html>";
            body = body.replace("{{UserName}}", userName)
                    .replace("{{OTP}}", generatedOtp)
                    .replace("{{YourAppName}}", "FixPoint");
            emailService.sendSimpleEmail(email, "Your One-Time Password (OTP) for Verification", body);
        }
        return isExist;
    }

    @Override
    public boolean verifyOtp(String email, String inputOtp) {
        Optional<Otp> latestOtpOpt = otpRepository.findTopByEmailOrderByCreatedAtDesc(email);
        if (latestOtpOpt.isEmpty()) {
            return false; // No OTP found
        }
        Otp latestOtp = latestOtpOpt.get();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = latestOtp.getCreatedAt().plusMinutes(5);
        boolean notExpired = now.isBefore(expiryTime);
        boolean otpMatches = latestOtp.getOtp().equals(inputOtp);
        return notExpired && otpMatches;
    }

    @Override
    public boolean verifyTPin(String email, String tPin) {
        Optional<User> byUserIdAndEmail = userRepository.findByEmailAndTPin(email, Integer.parseInt(tPin));
        return byUserIdAndEmail.isPresent();
    }
}
