package com.study.base.boot.aggregations.v1.item.domain.entity;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItemStock;
import com.study.base.boot.aggregations.v1.item.domain.ItemAggregate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(catalog = "base", name="item_stock")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class ItemStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stockQty;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private ItemAggregate item;

    public ItemStockEntity putItem(ItemAggregate item) {
        this.item = item;

        return this;
    }

    public ItemStockEntity patch(CreateItemStock createItemStock){
        this.stockQty = createItemStock.getStockQty();

        return this;
    }
}
