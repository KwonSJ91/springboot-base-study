package com.study.base.boot.aggregations.v1.auth.application;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

	@Value("${jwt.access-token.key}")
	private String accessKey;

	public boolean doAuthentication(String accessToken) {
		// final SecretKey signatureKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 서명
		final SecretKey signatureKey = Keys.hmacShaKeyFor(
			Base64.getEncoder()
				.encodeToString(accessKey.getBytes())
				.getBytes()
		);

		try {
			Jwts.parser()
				.setSigningKey(signatureKey)
				.build()
				// .parseClaimsJws(accessToken) // 이전버전
				.parseSignedClaims(accessToken);
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}

		return true;
	}
}
