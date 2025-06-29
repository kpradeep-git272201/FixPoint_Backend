package com.fixpoint.module.auth.controller;

import com.fixpoint.jwt.utils.JwtUtils;
import com.fixpoint.module.auth.service.OtpService;
import com.fixpoint.module.user.entity.User;
import com.fixpoint.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private OtpService otpService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/otp")
    public ResponseEntity<Object> generateOtp(@RequestBody Map<String, String> emailBody){
        String toEmail= emailBody.get("email");
        Map<String, String> res= new HashMap<>();
        boolean isExist = otpService.generateOtp(toEmail);
        if(isExist){
            return new ResponseEntity<>(res.put("messaage","Otp generated"), HttpStatus.OK);
        }
        return new ResponseEntity<>(res.put("messaage","Email not exist!"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyOtp(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String otp = requestBody.get("otp");
        boolean isValid = otpService.verifyOtp(email, otp);

        if (isValid) {
            User user = userRepository.findByEmail(email).get();
            String token = jwtUtil.generateToken(user);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            Map<String, Object> body = Map.of(
                    "userId", user.getId(),
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "roleIds", user.getRoleIds()
            );
            return ResponseEntity.ok().headers(headers).body(body);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP.");
        }
    }

    @GetMapping("/test")
    public String getTest(){
        return "I am running";
    }
}
