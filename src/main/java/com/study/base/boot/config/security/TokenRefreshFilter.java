package com.study.base.boot.config.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.study.base.boot.aggregations.v1.auth.application.AuthenticationService;
import com.study.base.boot.aggregations.v1.auth.application.AuthorizationService;
import com.study.base.boot.config.constants.HeaderConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TokenRefreshFilter extends OncePerRequestFilter {

	private final AuthenticationService authenticationService;
	private final AuthorizationService authorizationService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String accessToken = request.getHeader(HeaderConstants.X_ACCESS_TOKEN);
		final String refreshToken = request.getHeader(HeaderConstants.X_REFRESH_TOKEN);

		if(StringUtils.isNotEmpty(accessToken)) {
			final boolean isNeedRefresh = authenticationService.isNeedRefresh(accessToken);

			if(isNeedRefresh) {
				/*
				final String refreshedToken = authorizationService.refresh(refreshToken);

				request.setAttribute(HeaderConstants.X_ACCESS_TOKEN, refreshToken);
				*/
				accessToken =authorizationService.refresh(refreshToken);
				log.info("===> refreshedToken :: {}", accessToken);
			}
		}

		response.setHeader(HeaderConstants.X_ACCESS_TOKEN, accessToken);
		response.setHeader(HeaderConstants.X_REFRESH_TOKEN, refreshToken);

		log.info("===> refreshToken :: {}", refreshToken);

		filterChain.doFilter(request,response);
	}
}
