package com.java.msorder.mapper;

import com.java.msorder.dao.entity.OrderItemEntity;
import com.java.msorder.model.request.OrderItemRequest;
import com.java.msorder.model.response.OrderItemResponse;

public enum OrderItemMapper {
    ORDER_ITEM_MAPPER;

    public OrderItemEntity buildOrderItemEntity( OrderItemRequest orderItem){
        return OrderItemEntity.builder()
                .name(orderItem.getName())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .totalPrice(orderItem.getTotalPrice())
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
