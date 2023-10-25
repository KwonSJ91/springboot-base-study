package com.study.base.boot.aggregations.v1.order.application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;

import lombok.extern.slf4j.Slf4j;

@DisplayName("[서비스] order(주문)")
@ExtendWith(MockitoExtension.class) // 가상 임시 데이터
@Slf4j
class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	private CreateOrder createOrder;

	@BeforeEach
	void init(){
		createOrder = CreateOrder.builder().orderNumber("1115").orderName("주문5").price(100).deliveryFee(1).address("1").build();
	}


	@Nested
	public  class 주문 {
		@Test
		void get() {
			long id = 0L;
			//given -- 데이터 세팅
			id = orderService.create(createOrder);

			//when -- 실행
			final var orderAggregate = orderService.get(id);

			//then -- 결과
			assertAll(
				() -> assertNotNull(orderAggregate)
			);
		}

	}
}