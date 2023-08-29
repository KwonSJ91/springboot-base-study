package com.study.base.boot.aggregations.v1.item.presentation.dto.req;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItemStock;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateItemDto {

    @NotNull
    private String itemName;

    @PositiveOrZero
    private int price;

    @NotNull
    @Valid
    private CreateItemStockDto stock;

    public CreateItem toCreate() {
        return CreateItem.builder()
                .itemName(this.itemName)
                .price(this.price)
                .stock(
                    CreateItemStock.builder()
                    .stockQty(this.stock.getStockQty()
                    ).build()
                )
                .build();
    }
}