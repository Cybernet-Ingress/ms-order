package com.java.msorder.service.concrete;

import com.java.msorder.aop.Monitored;
import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.dao.entity.OrderItemEntity;
import com.java.msorder.dao.repository.OrderRepository;
import com.java.msorder.exception.NotFoundException;
import com.java.msorder.model.request.OrderItemRequest;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.response.OrderResponse;
import com.java.msorder.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.java.msorder.exception.ExceptionConstants.ORDER_NOT_FOUND_CODE;
import static com.java.msorder.exception.ExceptionConstants.ORDER_NOT_FOUND_MESSAGE;
import static com.java.msorder.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;
import static com.java.msorder.mapper.OrderMapper.ORDER_MAPPER;
import static com.java.msorder.model.enums.OrderStatus.CANCELED;

@Service
@RequiredArgsConstructor
@Monitored
public class OrderServiceHandler implements OrderService {


    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Long userId, OrderRequest orderRequest) {
        var order = ORDER_MAPPER.buildOrderEntity( orderRequest);
        order.setId(userId);
        var orderItems = orderRequest.getOrderItems();
        var orderItemEntities = orderItems.stream()
                .map(ORDER_ITEM_MAPPER::buildOrderItemEntity)
                .collect(Collectors.toList());
        order.setOrderItems(orderItemEntities);
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long id) {
        var orderEntities = orderRepository.findAllOrderByUserId(id);
        return orderEntities.stream()
                .map(ORDER_MAPPER::buildOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrderById(Long id) {
        var order = fetchOrderIfExist(id);
        order.setStatus(CANCELED);
        orderRepository.save(order);
    }

//    @Override
//    public void updateOrderStatus(Long id, OrderStatus status) {
//        var order=fetchOrderIfExist(id);
//        order.setStatus(status);
//        orderRepository.save(order);
//    }


    private OrderEntity fetchOrderIfExist(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_CODE, String.format(ORDER_NOT_FOUND_MESSAGE)));
    }
}
