package com.study.base.boot.aggregations.v1.order.presentation.dto.res;

import java.time.LocalDateTime;
import java.util.List;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseDto;
import com.study.base.boot.config.mapstruct.base.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class OrderDto extends BaseDto {
	private Long id;
	private String orderNumber;
	private String orderName;
	private OrderStatusEnum status;
	private int price;
	private int deliveryFee;
	private String address;
	private long userId;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	private List<OrderItemDto> items;
}
