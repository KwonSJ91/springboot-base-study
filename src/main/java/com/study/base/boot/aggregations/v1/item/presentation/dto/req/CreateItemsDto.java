package com.study.base.boot.aggregations.v1.item.presentation.dto.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateItemsDto {

    @NotNull
    @Valid
    @Size(min = 1)
    private List<CreateItemDto> createOrders;

}