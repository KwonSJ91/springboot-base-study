package com.study.base.boot.aggregations.v1.user.application;

import org.springframework.stereotype.Service;

import com.study.base.boot.aggregations.v1.auth.application.AuthorizationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final AuthorizationService authorizationService;

	public String login(final String userId, final String password) {
		// 원래는 회원 조회해서 PK값 가져오는 것으로 해야함
		final long id = 1L;
		final String accessToken = authorizationService.createAccessToken(id);

		authorizationService.createRefreshToken();

		return accessToken;
	}

}
