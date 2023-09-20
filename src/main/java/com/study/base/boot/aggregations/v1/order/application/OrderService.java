package com.study.base.boot.aggregations.v1.order.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Long create(CreateOrder createOrder) {
        final var orderAggregate = OrderAggregate.builder()
                .build()
                .patch(createOrder)
                .create(orderRepository);

        return orderAggregate.getId();
    }

    @Transactional(readOnly = true)
    public OrderAggregate get(long id) {
        Optional<OrderAggregate> byId = orderRepository.findById(id);
        OrderAggregate orderAggregate = byId.orElseGet(null);

        // List<OrderItemEntity> items = orderAggregate.getItems();

        return orderAggregate;
    }

    @Transactional(readOnly = true)
    public Page<OrderAggregate> listByStatus(OrderStatusEnum orderStatus, Pageable pageable) {

        Page<OrderAggregate> allByStatus = orderRepository.findAllByStatus(orderStatus, pageable);

        return allByStatus;
    }

    @Transactional(readOnly = true)
    public Page<OrderAggregate> listBySearch(OrderStatusEnum orderStatus, int price, LocalDateTime startOrderDate, LocalDateTime endOrderDate, Pageable pageable) {


        Page<OrderAggregate> allByStatus = orderRepository.findAllByStatusAndPriceGreaterThanEqualAndCreatedDateBetween(orderStatus, price, startOrderDate, endOrderDate, pageable);

        return allByStatus;
    }
}
