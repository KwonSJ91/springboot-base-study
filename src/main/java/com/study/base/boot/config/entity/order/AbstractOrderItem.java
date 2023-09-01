package com.study.base.boot.config.entity.order;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderItemStatusEnum;
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
public abstract class AbstractOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected long itemId;
	protected String itemName;

	@Enumerated(EnumType.STRING)
	protected OrderItemStatusEnum status;
	protected int price;
	protected int qty;

	@CreatedDate
	protected LocalDateTime createdDate;

	@LastModifiedDate
	protected LocalDateTime updatedDate;
}