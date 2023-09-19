package com.study.base.boot.config.mapstruct.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

import com.study.base.boot.config.mapstruct.base.BaseDto;
import com.study.base.boot.config.mapstruct.base.BaseEntity;
import com.study.base.boot.config.mapstruct.config.MapstructConfig;

@MapperConfig(
	unmappedSourcePolicy = ReportingPolicy.IGNORE,
	unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SupportEntityToDtoMapper<E extends BaseEntity, D extends BaseDto> extends MapstructConfig {

	E toEntity(D d);

	D toDto(E e);
}
