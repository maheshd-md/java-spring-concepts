package com.example.jwt_example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

public class JWTProvider {

    // Step 1: Generate a random Secret Key for HS256 signature algorithm
    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    
    public static String createToken(String username, Map<String, String> claims) {
        
        long issuedAt = System.currentTimeMillis();
        Date validity = new Date(issuedAt + 1000 * 60 * 10); // 10 minutes validity
		
        String jwtToken = Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setSubject(username)
				.setIssuer("my-app")   
				.claim("role", "user")
                .setClaims(claims)					// custom claims
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(validity)
                .signWith(key)
                .compact();
        
        System.out.println("Generated JWT Token: " + jwtToken);
        
        return jwtToken;
    }

    public static Claims getClaims(String jwtToken) {
    	
    	Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)                              // Set the same secret key
                .build()
                .parseClaimsJws(jwtToken)                        // Parse the JWT token
                .getBody();

        // Step 4: Access Claims
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Issued At: " + claims.getIssuedAt());
        System.out.println("Expiration: " + claims.getExpiration());
        System.out.println("Role: " + claims.get("role"));
        
        return claims;
    }
    
    
}
