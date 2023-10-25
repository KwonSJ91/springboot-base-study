package com.study.base.boot.aggregations.v1.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.study.base.boot.aggregations.v1.auth.application.AuthenticationService;
import com.study.base.boot.aggregations.v1.auth.application.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@DisplayName("[서비스] Authentication(인증)")
@ExtendWith(MockitoExtension.class) // 가상 임시 데이터
@Slf4j
class AuthenticationServiceTest {

	@InjectMocks
	private AuthenticationService authenticationService;
	@InjectMocks
	private AuthorizationService authorizationService;

	@BeforeEach
	void init(){
		ReflectionTestUtils.setField(authenticationService, "accessKey", "spring-boot-base-study-auth");
		ReflectionTestUtils.setField(authorizationService, "accessKey", "spring-boot-base-study-auth");
	}

	@Test
	void 토큰_인증(){
		long id = 0L;
		//given -- 데이터 세팅
		final var accessToken = authorizationService.createAccessToken(id);

		//when -- 실행
		final var valid = authenticationService.doAuthentication(accessToken);

		//then -- 결과
		assertAll(
			() -> assertTrue(valid)
		);
	}

}