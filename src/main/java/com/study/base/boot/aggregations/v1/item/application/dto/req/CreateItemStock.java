package com.study.base.boot.aggregations.v1.item.application.dto.req;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateItemStock {

    @PositiveOrZero
    private int stockQty;

}
