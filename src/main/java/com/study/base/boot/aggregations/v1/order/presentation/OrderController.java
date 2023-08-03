package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestApi("/v1/orders")
public class OrderController {

    @Get
    public List<String> getOrders() {
        return List.of("A","B","C");
    }

    @Post
    public List<String> createOrders(@RequestBody List<String> orderList ){
        return orderList;
    }

}
