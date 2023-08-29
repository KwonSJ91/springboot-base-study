package com.study.base.boot.aggregations.v1.item.presentation;

import com.study.base.boot.aggregations.v1.item.application.ItemService;
import com.study.base.boot.aggregations.v1.item.presentation.dto.req.CreateItemDto;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestApi("/v1/items")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Get
    public List<String> getItems() {
        return List.of("A","B","C");
    }

    @Post
    public Long createItem(@Valid @RequestBody CreateItemDto item) {
        itemService.create(item.toCreate());
        return 0L;
    }

}
