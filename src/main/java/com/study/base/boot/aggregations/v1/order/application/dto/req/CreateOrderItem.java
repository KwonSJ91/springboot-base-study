package com.study.base.boot.aggregations.v1.order.application.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderItem {

    @PositiveOrZero
    private long itemId;

    @NotNull
    private String itemName;

    @PositiveOrZero
    private int price;

    @PositiveOrZero
    private int qty;

    // private OrderItemStatusEnum status;
}
