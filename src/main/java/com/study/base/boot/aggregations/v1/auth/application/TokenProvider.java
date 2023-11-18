package com.study.base.boot.aggregations.v1.auth.application;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.study.base.boot.aggregations.v1.auth.infrastructure.redis.entity.RefreshToken;
import com.study.base.boot.aggregations.v1.auth.infrastructure.redis.repository.RefreshTokenRedisRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

	private final RefreshTokenRedisRepository refreshTokenRedisRepository;

	public String createRefreshToken(){
		// String token = "aaaa";
		final String token =  new Timestamp(System.currentTimeMillis()).toString();
		refreshTokenRedisRepository.save(RefreshToken.builder()
				.token(token)
				.role(List.of("USER"))
			.build());

		// final var byToken = refreshTokenRedisRepository.findByToken(token);

		return token;
	}

	public Optional<RefreshToken> get(String refreshToken) {
		final Optional<RefreshToken> info = refreshTokenRedisRepository.findByToken(refreshToken);

		return info;
	}
}
