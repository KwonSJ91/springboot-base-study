package com.study.base.boot.aggregations.v1.user.presentation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;

import com.study.base.boot.aggregations.v1.auth.application.AuthorizationService;
import com.study.base.boot.aggregations.v1.user.application.UserService;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.RestApi;
import com.study.base.boot.config.constants.HeaderConstants;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestApi("/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final AuthorizationService authorizationService;

	@Get("/login")
	public ResponseEntity<Void> login(HttpServletRequest request, String userId, String password) {
		final String accessToken = userService.login(userId, password);
		final String refreshToken = StringUtils.defaultString(
			request.getHeader(HeaderConstants.X_REFRESH_TOKEN),
			authorizationService.createRefreshToken()
		);

		return ResponseEntity.ok()
			.header("x-access-token", accessToken)
			.header(HeaderConstants.X_REFRESH_TOKEN, refreshToken)
			.build();
	}

}
