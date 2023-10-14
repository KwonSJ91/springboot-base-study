package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.OrderService;
import com.study.base.boot.aggregations.v1.order.application.dto.req.GetOrder;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.GetOrderDto;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.res.OrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.mapper.OrderEDMapper;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Patch;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.Put;
import com.study.base.boot.config.annotations.RestApi;
import com.study.base.boot.config.controller.SupportController;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestApi("/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController extends SupportController {

    private final OrderService orderService;


    private final OrderEDMapper orderEDMapper;

    @Get("/status/{status}")
    public Page<OrderDto> getOrders(@PathVariable OrderStatusEnum status, @RequestParam int price,
        @RequestParam("startOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOrderDate,
        @RequestParam("endOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endOrderDate,
        @PageableDefault(size = 10, sort="id", direction = Sort.Direction.DESC)
        Pageable pageable) {
        Page<OrderAggregate> pageOrders = orderService.listByStatus(status, pageable);
       /* Page<OrderAggregate> searchPageOrders = orderService.listBySearch(status, price, startOrderDate.atStartOfDay(), endOrderDate.atTime(
            LocalTime.MAX), pageable);*/
        List<OrderAggregate> orders = pageOrders.getContent();

        List<OrderDto> orderDtos = orders.stream()
            .map(order -> orderEDMapper.toDto(order))
            .collect(Collectors.toList());

        return new PageImpl<>(orderDtos, pageable, pageOrders.getTotalElements());
    }

    @Get
    public Page<OrderDto> getOrders(GetOrderDto request,
        @PageableDefault(size = 10, sort="id", direction = Sort.Direction.DESC)
        Pageable pageable) {
        final GetOrder getOrder = request.toGetOrder(pageable);
        final Page<OrderAggregate> pageOrders = orderService.list(getOrder);

        return response(orderEDMapper, pageOrders, pageable);
    }

    @Get("/date")
    public Page<OrderDto> getOrderDates(@RequestParam int price,
        @RequestParam("startOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOrderDate,
        @RequestParam("endOrderDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endOrderDate,
        @PageableDefault(size = 10, sort="id", direction = Sort.Direction.DESC)
        Pageable pageable) {
        //Page<OrderAggregate> pageOrders = orderService.listByStatus(status, pageable);
        Page<OrderAggregate> searchPageOrders = orderService.listBySearch(price, startOrderDate.atStartOfDay(), endOrderDate.plusDays(1).atStartOfDay(), pageable);
        List<OrderAggregate> orders = searchPageOrders.getContent();

        List<OrderDto> orderDtos = orders.stream()
            .map(order -> orderEDMapper.toDto(order))
            .collect(Collectors.toList());

        return new PageImpl<>(orderDtos, pageable, searchPageOrders.getTotalElements());
    }

    @Get("/{id}")
    public OrderDto getOrder(@PathVariable long id) {
        OrderAggregate orderAggregate = orderService.get(id);


        OrderDto orderDto = orderEDMapper.toDto(orderAggregate);

        return orderDto;
    }

    @Post("/order")
    public Long createOrder(@Valid @RequestBody CreateOrderDto payload) {
        orderService.create(payload.toCreate());
        return 0L;
    }

    /**
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

    @Patch("/{id}/status/{status}")
    public void changeOrderStatus(@PathVariable long id, @PathVariable OrderStatusEnum status) {
        orderService.changeStatus(id, status);
    }
}
