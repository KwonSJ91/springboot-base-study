package com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.req;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCondition {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int price;
	private Pageable pageable;
}
