package com.java.msorder.service.abstraction;

import com.java.msorder.model.request.OrderItemRequest;
import com.java.msorder.model.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    void createdOrderItem(Long id,OrderItemRequest orderItemRequest);
    List<OrderItemResponse> getOrderItemsByOrderId(Long id);

    OrderItemResponse getOrderItemById(Long id);



}
