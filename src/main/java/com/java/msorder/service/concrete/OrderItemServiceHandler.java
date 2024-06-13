package com.java.msorder.service.concrete;

import com.java.msorder.aop.Monitored;
import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.dao.entity.OrderItemEntity;
import com.java.msorder.dao.repository.OrderItemRepository;
import com.java.msorder.dao.repository.OrderRepository;
import com.java.msorder.exception.NotFoundException;
import com.java.msorder.model.request.OrderItemRequest;
import com.java.msorder.model.response.OrderItemResponse;
import com.java.msorder.service.abstraction.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.java.msorder.exception.ExceptionConstants.*;
import static com.java.msorder.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;

@Service
@RequiredArgsConstructor
@Monitored
public class OrderItemServiceHandler implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;


    @Override
    public void createdOrderItem(Long id, OrderItemRequest orderItemRequest) {
        var orderItemEntity = ORDER_ITEM_MAPPER.buildOrderItemEntity(orderItemRequest);
        orderItemEntity.setProductId(id);
        orderItemRepository.save(orderItemEntity);
    }

    @Override
    public List<OrderItemResponse> getOrderItemsByOrderId(Long id) {
        OrderEntity order = fetchOrderIfExist(id);
        var orderItemEntities = order.getOrderItems();
        return orderItemEntities.stream()
                .map(ORDER_ITEM_MAPPER::buildOrderItemResponse)
                .collect(Collectors.toList());
    }


    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        var orderItemEntity = fetchOrderItemIfExist(id);
        return ORDER_ITEM_MAPPER.buildOrderItemResponse(orderItemEntity);

    }

    OrderItemEntity fetchOrderItemIfExist(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_ITEM_NOT_FOUND_CODE, String.format(ORDER_ITEM_NOT_FOUND_MESSAGE)));
    }

    private OrderEntity fetchOrderIfExist(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_CODE, String.format(ORDER_NOT_FOUND_MESSAGE)));
    }
}
