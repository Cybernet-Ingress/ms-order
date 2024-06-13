package com.java.msorder.mapper;

import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.dao.entity.OrderItemEntity;
import com.java.msorder.model.enums.OrderStatus;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.response.OrderItemResponse;
import com.java.msorder.model.response.OrderResponse;

import java.time.LocalDate;
import java.util.List;

import static com.java.msorder.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

public enum OrderMapper {
    ORDER_MAPPER;


    public OrderEntity buildOrderEntity(OrderRequest orderRequest){
        return OrderEntity.builder()
                .status(OrderStatus.NEW)
                .orderAmount(orderRequest.getOrderAmount())
                .build();
    }

    public OrderResponse buildOrderResponse(OrderEntity order){
        return OrderResponse.builder()
                .orderAmount(order.getOrderAmount())
                .orderStatus(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
