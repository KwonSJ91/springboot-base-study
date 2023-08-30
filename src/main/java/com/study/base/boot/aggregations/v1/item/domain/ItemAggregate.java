package com.study.base.boot.aggregations.v1.item.domain;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.item.domain.entity.ItemStockEntity;
import com.study.base.boot.aggregations.v1.item.domain.enumerations.ItemStatusEnum;
import com.study.base.boot.aggregations.v1.item.repository.ItemRepository;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(catalog = "base", name = "item")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ItemAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private ItemStatusEnum status;
    private int price;
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ItemStockEntity stock;

    public ItemAggregate create(ItemRepository itemRepository) {
        itemRepository.save(this);

        return this;
    }

    public ItemAggregate patch(CreateItem createItem) {
        this.itemName = createItem.getItemName();
        this.price = createItem.getPrice();
        this.addStock(ItemStockEntity.builder().build().patch(createItem.getStock()));

        return this;
    }

    public ItemAggregate addStock(ItemStockEntity itemStock) {
        Assert.notNull(itemStock, "itemStock is null");

        itemStock.putItem(this);
        this.stock = itemStock;

        return this;

    }

}
