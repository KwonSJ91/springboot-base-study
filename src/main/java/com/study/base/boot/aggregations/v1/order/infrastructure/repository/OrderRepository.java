package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.req.OrderCondition;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.res.OrderInfoProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {

	Page<OrderAggregate> findAllByStatus(OrderStatusEnum status, Pageable pageable);

	Page<OrderAggregate> findAllByPriceGreaterThanEqualAndCreatedDateGreaterThanEqualAndCreatedDateLessThan(int price, LocalDateTime startCreatedDate, LocalDateTime endCreatedDate, Pageable pageable);

	Page<OrderAggregate> findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanAndPriceGreaterThanEqual(
		LocalDateTime startDate,
		LocalDateTime endDate,
		int price,
		Pageable pageable
	);

	@Query(value = """
		select order
		from OrderAggregate order
		join fetch order.items
		where order.createdDate >= :startDate
		and order.createdDate < :endDate
		and order.price >= :price
	""")
	Page<OrderAggregate> getOrders(LocalDateTime startDate, LocalDateTime endDate, int price, Pageable pageable);

	default Page<OrderAggregate> getOrders(OrderCondition condition) {
		// return findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanAndPriceGreaterThanEqual(
		return getOrders(
			condition.getStartDate(),
			condition.getEndDate(),
			condition.getPrice(),
			condition.getPageable()
		);
	}

	@Query("""
		update OrderAggregate order
		set order.status = :status
		where order.id = :id
	""")
	@Modifying
	void changeStatus(long id, OrderStatusEnum status);

	/*
	@Query(value = """
        select new com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.res.OrderInfoProjection(
            order.orderNumber,
            order.status,
            order.price,
            order.createdDate
        )
        from OrderAggregate order 
        where order.createdDate >= :startDate 
        and order.createdDate < :endDate
        and order.price >= :price
    """)
	List<OrderInfoProjection> getOrders(LocalDateTime startDate, LocalDateTime endDate, int price);
*/
}
