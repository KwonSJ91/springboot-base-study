package com.study.base.boot.aggregations.v1.order.presentation.mapper;

import java.util.List;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.presentation.dto.res.OrderDto;
import com.study.base.boot.config.mapstruct.base.BaseEntity;
import com.study.base.boot.config.mapstruct.mapper.SupportEntityToDtoMapper;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderEDMapper extends SupportEntityToDtoMapper<OrderAggregate, OrderDto> {
/*
	@Condition
	default  boolean isLazyLoaded(List<? extends BaseEntity> entities) {
		return isLoaded(entities);
	}
*/
}
