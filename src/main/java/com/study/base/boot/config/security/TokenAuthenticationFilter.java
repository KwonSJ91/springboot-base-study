package com.study.base.boot.config.security;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.study.base.boot.aggregations.v1.auth.application.AuthenticationService;
import com.study.base.boot.aggregations.v1.user.application.UserService;
import com.study.base.boot.aggregations.v1.user.domain.User;
import com.study.base.boot.config.constants.HeaderConstants;
import com.study.base.boot.config.security.dto.AuthUserInfo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private final AuthenticationService authenticationService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		final var accessToken = response.getHeader(HeaderConstants.X_ACCESS_TOKEN);

		log.info("===> accessToken :: {}", accessToken);

		authenticationService.doAuthentication(accessToken);

		filterChain.doFilter(request, response);

	}
}
