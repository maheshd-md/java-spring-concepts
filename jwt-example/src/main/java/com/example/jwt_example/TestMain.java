package com.example.jwt_example;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;

public class TestMain {

	public static void main(String[] args) {
		
		JWTProvider jwtProvider = new JWTProvider();
		
		Map<String, String> customClaims = new HashMap<>();
		customClaims.put("test", "Test");
		String jwtToken = jwtProvider.createToken("test-user", customClaims);
		Claims claims = jwtProvider.getClaims(jwtToken);
	}
}
