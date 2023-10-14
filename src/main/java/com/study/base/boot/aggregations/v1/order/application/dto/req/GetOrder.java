package com.study.base.boot.aggregations.v1.order.application.dto.req;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.req.OrderCondition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetOrder {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int price;
	private Pageable pageable;

	public OrderCondition toCondition() {
		return OrderCondition.builder()
			.startDate(startDate)
			.endDate(endDate)
			.price(price)
			.pageable(pageable)
			.build();
	}
}
