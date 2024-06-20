package com.java.msorder.controller;

import com.java.msorder.model.response.OrderResponse;
import com.java.msorder.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/internal/orders")
@RequiredArgsConstructor
public class InternalOrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long userId){
       return orderService.getOrdersByUserId(userId);
    }
}
