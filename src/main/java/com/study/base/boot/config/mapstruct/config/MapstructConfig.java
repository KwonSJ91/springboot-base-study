package com.study.base.boot.config.mapstruct.config;

import java.util.List;

import org.hibernate.Hibernate;
import org.mapstruct.MapperConfig;

import com.study.base.boot.config.mapstruct.base.BaseEntity;

@MapperConfig
public interface MapstructConfig {

	default boolean isLoaded(List<? extends BaseEntity > entities) {
		return Hibernate.isInitialized(entities);
	}

	default  boolean isLoaded(BaseEntity entity) {
		return Hibernate.isInitialized(entity);
	}

}
