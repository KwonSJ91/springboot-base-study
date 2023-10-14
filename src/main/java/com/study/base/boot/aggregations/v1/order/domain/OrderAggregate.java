package com.study.base.boot.aggregations.v1.order.domain;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderItemStatusEnum;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import com.study.base.boot.config.entity.order.AbstractOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(catalog = "base", name ="order")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@SuperBuilder
@Getter
public class OrderAggregate extends AbstractOrder {

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> items;

    public static final List<OrderAggregate> creates(OrderRepository orderRepository, List<CreateOrder> createOrders) {
        Assert.notEmpty(createOrders, "createOrders is null");

        final var orders = createOrders.stream()
            .map(createOrder ->
                OrderAggregate.builder()
                    .build()
                    .patch(createOrder)
            ).collect(Collectors.toList());

        orderRepository.saveAll(orders);

        return orders;
    }

    public OrderAggregate create(OrderRepository orderRepository) {
        orderRepository.save(this);

        return this;
    }

    public OrderAggregate patch(CreateOrder createOrder) {
        this.orderNumber = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.orderNumber);
        this.orderName = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.orderName);
        this.price = createOrder.getPrice();
        this.deliveryFee = createOrder.getDeliveryFee();
        this.address = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.address);
        this.userId = createOrder.getUserId();

        createOrder.getItems()
            .forEach(item -> this.addItem(
                OrderItemEntity.builder()
                    .build()
                    .patch(item)
            ));

        return this;
    }

    public OrderAggregate addItem(OrderItemEntity orderItem) {
        Assert.notNull(orderItem, "orderItem is null");

        if (this.getItems() == null) {
            this.items = new ArrayList<>();
        }

        orderItem.putOrder(this);
        this.items.add(orderItem);

        return this;
    }

    public void chageOrder(){
        this.status = OrderStatusEnum.ORDER;
    }

    public void chageCanceled(){
        this.status = OrderStatusEnum.CANCELED;
    }
}