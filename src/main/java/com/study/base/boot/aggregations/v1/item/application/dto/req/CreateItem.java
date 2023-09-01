package com.study.base.boot.aggregations.v1.item.application.dto.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateItem {
    @NotNull
    private String itemName;
    private int price;

    @Valid
    private CreateItemStock stock;
}
