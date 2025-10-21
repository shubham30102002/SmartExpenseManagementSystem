package com.expenseManagement.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenseManagement.sys.dto.EmailRequestDTO;
import com.expenseManagement.sys.dto.OtpVerificationDTO;
import com.expenseManagement.sys.service.OtpService;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
	@Autowired
	private OtpService otpService;

	@PostMapping("/send")
	public ResponseEntity<String> sendOtp(@RequestBody EmailRequestDTO request) {
		return ResponseEntity.ok(otpService.sendOtp(request));
	}

	@PostMapping("/verify")
	public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationDTO request) {
		String response = otpService.verifyOtp(request) ? "OTP verified successfully." : "Invalid or expired OTP.";
		return ResponseEntity.ok(response);
	}
}
