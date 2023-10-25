package com.study.base.boot.aggregations.v1.user.presentation;

import org.springframework.http.ResponseEntity;

import com.study.base.boot.aggregations.v1.user.application.UserService;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.RestApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestApi("/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Get("/login")
	public ResponseEntity<Void> login(String userId, String password) {
		final var accessToken = userService.login(userId, password);

		return ResponseEntity.ok()
			.header("x-access-token", accessToken)
			.build();
	}

}
