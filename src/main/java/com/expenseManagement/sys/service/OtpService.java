package com.expenseManagement.sys.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseManagement.sys.aspect.LogExecutionTime;
import com.expenseManagement.sys.dto.EmailRequestDTO;
import com.expenseManagement.sys.dto.OtpVerificationDTO;
import com.expenseManagement.sys.entity.OtpVerification;
import com.expenseManagement.sys.repo.OtpVerificationRepository;

@Service
public class OtpService {
	
	@Autowired
	private OtpVerificationRepository otpVerificationRepository;
	
	@Autowired
	private EmailService emailService;
	
	@LogExecutionTime
	public String sendOtp(EmailRequestDTO request) {
		String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
		LocalDateTime now = LocalDateTime.now().plusMinutes(3);
		
		OtpVerification otpEntity = otpVerificationRepository.findByEmail(request.getEmail())
				.orElse(new OtpVerification());
		otpEntity.setEmail(request.getEmail());
		otpEntity.setOtp(otp);
		otpEntity.setExpiryTime(now);
		otpEntity.setVerified(false);
		otpVerificationRepository.save(otpEntity);
		
		String message = "Your OTP for Smart Expense registration is " + otp + ". It is valid for 3 minutes.";
		
		return "OTP sent successfully to " + request.getEmail();
	}
	
	
	public boolean verifyOtp(OtpVerificationDTO request) {
		OtpVerification record = otpVerificationRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("OTP not found for email: " + request.getEmail()));
		
		if (record.isVerified()) {
			System.out.println("OTP already verified for email: " + request.getEmail());
			return true;
		}
		
		if (record.getExpiryTime().isBefore(LocalDateTime.now())) {
			System.out.println("OTP expired for email: " + request.getEmail());
			return false;
		}
		
		if (record.getOtp().equals(request.getOtp())) {
			record.setVerified(true);
			otpVerificationRepository.save(record);
			System.out.println("OTP verified for email: " + request.getEmail());
			return true;
		} else {
			System.out.println("Invalid OTP for email: " + request.getEmail());
			return false;
		}
	}
	
	
	public boolean isEmailVerified(String email) {
		  return otpVerificationRepository.findByEmail(email)
	                .map(OtpVerification::isVerified)
	                .orElse(false);
	}
}
