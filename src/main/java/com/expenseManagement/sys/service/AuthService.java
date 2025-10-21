package com.expenseManagement.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expenseManagement.sys.dto.LoginRequestDto;
import com.expenseManagement.sys.dto.UserDTO;
import com.expenseManagement.sys.entity.Role;
import com.expenseManagement.sys.entity.User;
import com.expenseManagement.sys.repo.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private OtpService otpService;
	
	/**
	 * Registers a new user in the system.
	 * @param userDTO
	 * @return 
	 */
	public boolean register(UserDTO userDTO) {
	    if (!otpService.isEmailVerified(userDTO.getEmail())) {
	    	return false;
	    }
	    
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(Role.USER);
		userRepository.save(user);
		return true;
	}
	
	/**
	 * Logs in a user and returns a JWT token.
	 * @param loginRequest
	 * @return
	 */
	public String login(LoginRequestDto loginRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		User user = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		return jwtService.generateToken(userDetails);
		
	}
}
