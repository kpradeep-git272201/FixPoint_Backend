package com.fixpoint.module.auth.service;

public interface OtpService {
    public boolean generateOtp(String email);
    public boolean verifyOtp(String email, String inputOtp);
    public boolean verifyTPin(String  email, String tPin);
}
