package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.OrderService;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestApi("/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Get
    public List<String> getOrders() {
        return List.of("A","B","C");
    }

    @Post
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
        List<Long> answer = null;
        Long createId = 0L;

        for (int i = 0; i < orders.size(); i++) {
            createId = orderService.create(orders.get(i).toCreate());
            answer.add(createId);
        }

        return answer;
    }
}
