package com.study.base.boot.aggregations.v1.order.domain.enumerations;

public enum OrderItemStatusEnum {

    ORDER("주문"),
    CANCELED("취소"),
    PARTIAL_CANCELED("주문취소"),
    SELL("판매중"),
    STOP("판매중단")
    ;

    private String status;

    OrderItemStatusEnum(String status) {
        this.status = status;
    }
}
