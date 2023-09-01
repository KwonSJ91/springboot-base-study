package com.study.base.boot.aggregations.v1.item.application;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.item.domain.ItemAggregate;
import com.study.base.boot.aggregations.v1.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long create(CreateItem createItem) {
        final var itemAggregate = ItemAggregate.builder()
                .build()
                .patch(createItem)
                .create(itemRepository);

        return itemAggregate.getId();
    }

}
