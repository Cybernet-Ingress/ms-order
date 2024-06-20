package com.java.msorder.mapper;

import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.dao.entity.OrderItemEntity;
import com.java.msorder.model.enums.OrderStatus;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.response.OrderResponse;
import com.java.msorder.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.java.msorder.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

public enum OrderMapper {
    ORDER_MAPPER;


    public OrderEntity buildOrderEntity(Long userId, OrderRequest orderRequest) {
        List<OrderItemEntity> orderItemEntities = orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> ORDER_ITEM_MAPPER.buildOrderItemEntity(orderItemRequest))
                .collect(Collectors.toList());
        OrderEntity order = OrderEntity.builder()
                .status(OrderStatus.NEW)
                .orderAmount(calculateOrderAmount(orderItemEntities))
                .orderItems(orderItemEntities)
                .userId(userId)
                .build();
        orderItemEntities.forEach(orderItemEntity -> orderItemEntity.setOrderEntity(order));
        return order;
    }

    public OrderResponse buildOrderResponse(OrderEntity order) {
        return OrderResponse.builder()
                .orderAmount(order.getOrderAmount())
                .createdAt(order.getCreatedAt())
                .orderItemResponses(order.getOrderItems().stream().map(ORDER_ITEM_MAPPER::buildOrderItemResponse).collect(Collectors.toList()))
                .build();
    }


    private BigDecimal calculateOrderAmount(List<OrderItemEntity> orderItemEntities) {
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            BigDecimal totalPrice = orderItemEntity.getTotalPrice();
            orderAmount = orderAmount.add(totalPrice);
        }
        return orderAmount;
    }


    public PageableResponse<OrderResponse> buildPageableOrderResponse(Page<OrderEntity> orderPage) {
        return PageableResponse.<OrderResponse>builder()
                .data(orderPage.getContent().stream().map(ORDER_MAPPER::buildOrderResponse).collect(Collectors.toList()))
                .hasNextPage(orderPage.hasNext())
                .lastPageNumber(orderPage.getTotalPages())
                .totalElements(orderPage.getTotalElements())
                .build();
    }

}
