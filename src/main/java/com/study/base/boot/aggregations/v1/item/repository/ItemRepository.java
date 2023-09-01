package com.study.base.boot.aggregations.v1.item.repository;

import com.study.base.boot.aggregations.v1.item.domain.ItemAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemAggregate, Long> {
}
