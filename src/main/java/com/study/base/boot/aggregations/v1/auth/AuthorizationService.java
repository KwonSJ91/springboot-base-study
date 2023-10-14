package com.study.base.boot.aggregations.v1.auth;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {

	@Value("${jwt.access-token.key}")
	private String accessKey;

	public String createAccessToken(){
		final String issuer = "BASE"; // ACCESS TOKEN 생성시 보통 서버 명을 넣는다.
		final String subject = "access"; // ACCESS TOKEN 이므로 제목을 access로 준다.
		final String audience = "1"; // 발급 대상 (로그인 유저의 pk)
		final Date expiredAt = Date.from(Instant.now().plus(Duration.ofDays(1L))); // 1 일
		final Date notBeforeAt = Date.from(Instant.now()); // 토클 발급 시점부터 사용 가능
		final Date issuedAt = Date.from(Instant.now()); // qkfrmq tlrks
		final String jwtId = UUID.randomUUID().toString(); // jwt 식별 id
		//final SecretKey signatureKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 서명
		final SecretKey signatureKey = Keys.hmacShaKeyFor(
			Base64.getEncoder()
				.encodeToString(accessKey.getBytes())
				.getBytes()
		);

		final String accessToken = Jwts.builder()
			.issuer(issuer)			// 발급자(iss)
			.subject(subject)		// 제목(sub)
			// .audience(audience)		// 발급 대상(aud)
			.expiration(expiredAt)	// 만료시간(exp)
			.notBefore(notBeforeAt)	// 토큰 활성 날짜 (nbf)
			.issuedAt(issuedAt)		// 발급시간(iat)
			.id(jwtId)				// jwt 식별자(jti)
			.signWith(signatureKey)	// 서명 키
			.compact();

		return accessToken;
	}
}
