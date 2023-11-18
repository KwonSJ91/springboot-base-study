package com.study.base.boot.aggregations.v1.user.domain;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.study.base.boot.config.security.dto.AuthUserInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class User {

	private long id;
	private String userId;
	private String userName;
	private List<GrantedAuthority> roles;

	public AuthUserInfo toAuthUser() {
		return AuthUserInfo.builder()
			.id(id)
			.userId(userId)
			.userName(userName)
			.roles(roles)
			.build();
	}

}
