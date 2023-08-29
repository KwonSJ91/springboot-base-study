package com.study.base.boot.aggregations.v1.item.domain.enumerations;

import lombok.Getter;

@Getter
public enum ItemStatusEnum {

    SELL("판매중"),
    STOP("판매중지")
    ;

    private String status;

    ItemStatusEnum(String status) {
        this.status = status;
    }
}
