package com.hhk.expensetreackerapi.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder()
	            .claims(claims)               
	            .subject(subject)             
	            .issuedAt(new Date())          
	            .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
	            .signWith(getSigningKey())                
	            .compact();
	}
	
	public String getUserNameFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getSubject);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts
				.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claimsResolver.apply(claims);

	}

	public boolean validateToken(String jwtToken, UserDetails userDetails) {

		final String userName = getUserNameFromToken(jwtToken);

		return userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);

	}

	private boolean isTokenExpired(String jwtToken) {
		final Date expiration = getExpirationDateFromToken(jwtToken);
		return expiration.before(new Date());

	}

	private Date getExpirationDateFromToken(String jwtToken) {
		return getClaimFromToken(jwtToken, Claims::getExpiration);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
