package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.OrderService;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.res.OrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.res.OrderItemDto;
import com.study.base.boot.aggregations.v1.order.presentation.mapper.OrderEDMapper;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestApi("/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    private final OrderEDMapper orderEDMapper;

    @Get("/status/{status}")
    public Page<OrderDto> getOrders(@PathVariable OrderStatusEnum status, @RequestParam int price,
        @RequestParam("startOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startOrderDate,
        @RequestParam("endOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endOrderDate,
        @PageableDefault(size = 10, sort="id", direction = Sort.Direction.DESC)
        Pageable pageable) {
        //Page<OrderAggregate> pageOrders = orderService.listByStatus(status, pageable);
        Page<OrderAggregate> searchPageOrders = orderService.listBySearch(status, price, startOrderDate, endOrderDate, pageable);
        List<OrderAggregate> orders = searchPageOrders.getContent();

        List<OrderDto> orderDtos = orders.stream()
            .map(order -> orderEDMapper.toDto(order))
            .collect(Collectors.toList());

        return new PageImpl<>(orderDtos, pageable, searchPageOrders.getTotalElements());
    }

    @Get("/{id}")
    public OrderDto getOrder(@PathVariable long id) {
        OrderAggregate orderAggregate = orderService.get(id);

       // List<OrderItemEntity> items = orderAggregate.getItems();
/*
        final List<OrderItemDto> itemsDtos = items.stream()
            .map(item ->
                    OrderItemDto.builder()
                        .id(item.getId())
                        .itemId(item.getItemId())
                        .itemName(item.getItemName())
                        .status(item.getStatus())
                        .price(item.getPrice())
                        .qty(item.getQty())
                        .createdDate(item.getCreatedDate())
                        .updatedDate(item.getUpdatedDate())
                        .build()
                ).collect(Collectors.toList());

        return OrderDto.builder()
            .id(orderAggregate.getId())
            .orderNumber(orderAggregate.getOrderNumber())
            .orderName(orderAggregate.getOrderName())
            .status(orderAggregate.getStatus())
            .price(orderAggregate.getPrice())
            .deliveryFee(orderAggregate.getDeliveryFee())
            .address(orderAggregate.getAddress())
            .userId(orderAggregate.getUserId())
            .createdDate(orderAggregate.getCreatedDate())
            .updatedDate(orderAggregate.getUpdatedDate())
            .items(itemsDtos)
            .build();
            */

        OrderDto orderDto = orderEDMapper.toDto(orderAggregate);

        return orderDto;
    }

    @Post("/order")
    public Long createOrder(@Valid @RequestBody CreateOrderDto payload) {
        orderService.create(payload.toCreate());
        return 0L;
    }

    /**
      {
        "createOrders": [
          {
            "orderNumber": "주문번호1",
            "orderName": "주문명1",
            "price": 1000,
            "deliveryFee": 100,
            "address": "서울시",
            "userId": 1
          },
          {
            "orderNumber": "주문번호2",
            "orderName": "주문명2",
            "price": 2000,
            "deliveryFee": 200,
            "address": "서울시",
            "userId": 2
          }
        ]
      }
     * 테스트용
     * @param request
     * @return
     */
    @Post
    public List<Long> createOrders(@RequestBody @Valid CreateOrdersDto request){
        final var orders = request.getCreateOrders();
        List<Long> answer = new ArrayList<>();
        Long createId = 0L;

        for (int i = 0; i < orders.size(); i++) {
            createId = orderService.create(orders.get(i).toCreate());
            answer.add(createId);
        }

        return answer;
    }
}
