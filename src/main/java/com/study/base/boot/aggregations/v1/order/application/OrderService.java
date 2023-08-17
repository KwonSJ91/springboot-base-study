package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Long create(CreateOrder createOrder) {
        final var orderAggregate = OrderAggregate.builder()
                .build()
                .patch(createOrder)
                .create(orderRepository);

        return orderAggregate.getId();

    }

    public List<Long> creates(CreateOrdersDto createOrdersDto) {
        List<CreateOrderDto> createOrders = createOrdersDto.getCreateOrders();
        List<Long> answer = null;

        for (int i = 0; i < createOrders.size(); i++) {
            answer.add(this.create(createOrders.get(i).toCreate()));
        }

        return answer;
    }
}
