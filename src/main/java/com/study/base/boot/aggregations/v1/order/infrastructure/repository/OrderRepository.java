package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {

	Page<OrderAggregate> findAllByStatus(OrderStatusEnum status, Pageable pageable);

	Page<OrderAggregate> findAllByStatusAndPriceGreaterThanEqualAndCreatedDateBetween(OrderStatusEnum status, int price, LocalDateTime startCreatedDate, LocalDateTime endCreatedDate, Pageable pageable);
}
