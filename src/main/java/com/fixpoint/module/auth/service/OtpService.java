package com.fixpoint.module.auth.service;

public interface OtpService {
    public boolean generateOtp(String email);
    public boolean verifyOtp(String email, String inputOtp);
}
