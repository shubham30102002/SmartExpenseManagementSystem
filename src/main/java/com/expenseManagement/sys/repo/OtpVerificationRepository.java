package com.expenseManagement.sys.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenseManagement.sys.entity.OtpVerification;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
	 Optional<OtpVerification> findByEmail(String email);
}
