package com.study.base.boot.config.controller;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.study.base.boot.config.mapstruct.base.BaseEntity;
import com.study.base.boot.config.mapstruct.mapper.SupportEntityToDtoMapper;

public abstract class SupportController {

	public Page response(SupportEntityToDtoMapper mapper, Page<? extends BaseEntity> page, Pageable pageable) {
		final var content = page.getContent();
		final var responses = content.stream()
			.map(c -> mapper.toDto(c))
			.collect(Collectors.toList());

		return new PageImpl<>(responses, pageable, page.getTotalElements());
	}
}
