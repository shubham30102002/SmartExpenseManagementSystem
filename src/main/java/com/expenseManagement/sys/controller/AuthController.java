package com.expenseManagement.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenseManagement.sys.dto.LoginRequestDto;
import com.expenseManagement.sys.dto.OtpVerificationDTO;
import com.expenseManagement.sys.dto.UserDTO;
import com.expenseManagement.sys.service.AuthService;
import com.expenseManagement.sys.service.OtpService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@Autowired
	private OtpService otpService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO registerRequest) {
		boolean isRegistered = authService.register(registerRequest);
		if (isRegistered) {
			return ResponseEntity.ok("Registration successful! OTP sent to email.");
		} else {
			return ResponseEntity.badRequest().body("User already exists or registration failed.");
		}
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationDTO otpRequest) {
		boolean verified = otpService.verifyOtp(otpRequest);
		if (verified) {
			return ResponseEntity.ok("OTP verified successfully. You can now log in.");
		} else {
			return ResponseEntity.badRequest().body("Invalid or expired OTP.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequest) {
		String token = authService.login(loginRequest);
		if (token != null) {
			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.badRequest().body("Invalid email or password.");
		}
	}

}
