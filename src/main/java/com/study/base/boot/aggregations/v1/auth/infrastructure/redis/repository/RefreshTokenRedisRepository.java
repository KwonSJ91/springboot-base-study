package com.study.base.boot.aggregations.v1.auth.infrastructure.redis.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.study.base.boot.aggregations.v1.auth.infrastructure.redis.entity.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);
}
