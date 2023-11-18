package com.study.base.boot.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.study.base.boot.aggregations.v1.auth.application.AuthenticationService;
import com.study.base.boot.aggregations.v1.auth.application.AuthorizationService;
import com.study.base.boot.aggregations.v1.user.application.UserService;
import com.study.base.boot.config.security.handler.AccessDeniedHandler;
import com.study.base.boot.config.security.handler.EntryPointHandler;

import lombok.RequiredArgsConstructor;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationService authenticationService;
	private final AuthorizationService authorizationService;

	private final EntryPointHandler entryPointHandler;
	private final AccessDeniedHandler accessDeniedHandler;

	private static final String[] WHITE_LIST = {
		"/swagger-ui/**",
		"/swagger-resources/**",
		"/v3/api-docs/**",
		"/api-docs/**",
		"/v1/user/login/**"
	};

	@Bean
	protected SecurityFilterChain config(HttpSecurity http) throws  Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize ->
				authorize
					.requestMatchers(WHITE_LIST).permitAll()
					.requestMatchers("/v1/orders").hasRole("USER")
					.requestMatchers("/v1/orders/**").authenticated()
			)
			.exceptionHandling(handler ->
				handler
					.authenticationEntryPoint(entryPointHandler)
					.accessDeniedHandler(accessDeniedHandler)
			)
			.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(tokenRefreshFilter(), TokenAuthenticationFilter.class)
		;

		return http.build();
	}

	public TokenAuthenticationFilter tokenAuthenticationFilter(){
		return new TokenAuthenticationFilter(authenticationService);
	}

	public TokenRefreshFilter tokenRefreshFilter(){
		return new TokenRefreshFilter(authenticationService, authorizationService);
	}
}
