package com.jwt.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTHelper {
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);;
	
	public String getusernamefromToken(String token) {
		return getClaimfromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDatefromToken(String token) {
		return getClaimfromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimfromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsfromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims getAllClaimsfromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDatefromToken(token);
        return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userdetail) {
		Map<String, Object> claims = new HashMap();
        return doGenerateToken(claims, userdetail.getUsername());

	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getusernamefromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
