package com.study.base.boot.config.entity.order;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String orderNumber;
	protected String orderName;

	@Enumerated(EnumType.STRING)
	protected OrderStatusEnum status;
	protected int price;
	protected int deliveryFee;
	protected String address;
	protected long userId;

	@CreatedDate
	protected LocalDateTime createdDate;

	@LastModifiedDate
	protected LocalDateTime updatedDate;
}