package com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.res;

import java.time.LocalDateTime;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderInfoProjection {

	private String orderNumber;
	private OrderStatusEnum status;
	private int price;
	private LocalDateTime createdDate;
}
