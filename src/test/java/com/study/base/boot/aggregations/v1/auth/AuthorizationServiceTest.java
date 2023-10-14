package com.study.base.boot.aggregations.v1.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import lombok.extern.slf4j.Slf4j;

@DisplayName("[서비스] Authorization(인가)")
@ExtendWith(MockitoExtension.class) // 가상 임시 데이터
@Slf4j
class AuthorizationServiceTest {

	@InjectMocks
	private AuthorizationService authorizationService;

	@BeforeEach
	void init(){
		ReflectionTestUtils.setField(authorizationService, "accessKey", "spring-boot-base-study-auth");
	}
	@Test
	void 토큰_생성(){
		//given -- 데이터 세팅

		//when -- 실행
		final var accessToken = authorizationService.createAccessToken();

		//then -- 결과
		assertAll(
			() -> assertNotNull(accessToken)
		);

		log.info("accessToken :: {}", accessToken);
	}
}