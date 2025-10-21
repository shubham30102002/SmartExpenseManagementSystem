package com.expenseManagement.sys.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${jwt.expiration.ms}")
	private long JWT_EXPIRATION_MS;
	
	/**
	 * Generate token for given user
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		// Logic to generate JWT token using username, JWT_SECRET, and JWT_EXPIRATION_MIN
		return generateToken(new HashMap<>(), userDetails);
	}
	
	/**
	 * Generate token with extra claims
	 * @param extraClaims
	 * @param userDetails
	 * @return
	 */
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		// Logic to generate JWT token using extraClaims, username, JWT_SECRET, and JWT_EXPIRATION_MIN
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS * 60 * 1000))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	  /**
     * Extract username (subject) from JWT.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract any specific claim from token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Validate token — checks username and expiration.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
