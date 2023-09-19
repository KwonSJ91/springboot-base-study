package com.study.base.boot.aggregations.v1.order.presentation.dto.res;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderItemStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseDto;
import com.study.base.boot.config.mapstruct.base.BaseEntity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OrderItemDto extends BaseDto {
	
	private Long id;
	private long itemId;
	private String itemName;
	private OrderItemStatusEnum status;
	private int price;
	private int qty;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
