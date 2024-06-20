package com.java.msorder.mapper;

import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.dao.entity.OrderItemEntity;
import com.java.msorder.model.request.OrderItemRequest;
import com.java.msorder.model.response.OrderItemResponse;

import java.math.BigDecimal;

public enum OrderItemMapper {
    ORDER_ITEM_MAPPER;

    public OrderItemEntity buildOrderItemEntity(OrderItemRequest orderItemRequest) {
        return OrderItemEntity.builder()
                .name(orderItemRequest.getName())
                .quantity(orderItemRequest.getQuantity())
                .unitPrice(orderItemRequest.getUnitPrice())
                .totalPrice(BigDecimal.valueOf(orderItemRequest.getQuantity()).multiply(orderItemRequest.getUnitPrice()))
                .build();
    }

    public OrderItemResponse buildOrderItemResponse(OrderItemEntity entity){
        return OrderItemResponse.builder()
                .name(entity.getName())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .totalPrice(entity.getTotalPrice())
                .build();
    }
}
