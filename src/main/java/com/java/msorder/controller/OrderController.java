package com.java.msorder.controller;

import com.java.msorder.model.enums.OrderStatus;
import com.java.msorder.model.request.OrderCriteria;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.request.PageCriteria;
import com.java.msorder.model.response.OrderItemResponse;
import com.java.msorder.model.response.OrderResponse;
import com.java.msorder.model.response.PageableResponse;
import com.java.msorder.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createOrder(@RequestHeader(name = "USER_ID") Long userId, @RequestBody OrderRequest orderRequest) {
        orderService.createOrder(userId, orderRequest);
    }

    @GetMapping("/{id}")
    public List<OrderResponse> getOrdersByUserId(@RequestHeader(name = "USER_ID") Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/items/{id}")
    public List<OrderItemResponse> getOrderItemsByOrderId(@PathVariable Long id) {
        return orderService.getOrderItemsByOrderId(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus orderStatus) {
        orderService.updateOrderStatus(id, orderStatus);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }


    @GetMapping()
    public PageableResponse<OrderResponse> getOrders(PageCriteria pageCriteria, OrderCriteria orderCriteria) {
        return orderService.getOrders(orderCriteria, pageCriteria);
    }
}
