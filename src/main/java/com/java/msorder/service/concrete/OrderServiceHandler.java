package com.java.msorder.service.concrete;

import com.java.msorder.aop.Monitored;
import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.dao.repository.OrderRepository;
import com.java.msorder.exception.NotFoundException;
import com.java.msorder.model.enums.OrderStatus;
import com.java.msorder.model.request.OrderCriteria;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.request.PageCriteria;
import com.java.msorder.model.response.OrderItemResponse;
import com.java.msorder.model.response.OrderResponse;
import com.java.msorder.model.response.PageableResponse;
import com.java.msorder.service.abstraction.OrderService;
import com.java.msorder.service.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Long userId, OrderRequest orderRequest) {
        var order = ORDER_MAPPER.buildOrderEntity(userId, orderRequest);
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
    public PageableResponse<OrderResponse> getOrders(OrderCriteria orderCriteria, PageCriteria pageCriteria) {
        var pageRequest= PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(),Sort.by("id").descending());
        var orderPage=orderRepository.findAll(new OrderSpecification(orderCriteria),pageRequest);
        return ORDER_MAPPER.buildPageableOrderResponse(orderPage);

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
    public void updateOrderStatus(Long id, OrderStatus orderStatus) {
        OrderEntity orderEntity = fetchOrderIfExist(id);
        orderEntity.setStatus(orderStatus);
        orderRepository.save(orderEntity);
    }

    @Override
    public void deleteOrderById(Long id) {
        OrderEntity orderEntity = fetchOrderIfExist(id);
        orderEntity.setStatus(CANCELED);
        orderRepository.save(orderEntity);
    }


    private OrderEntity fetchOrderIfExist(Long id) {
        return orderRepository.findByIdAndStatusNotCanceled(id, CANCELED).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_CODE, String.format(ORDER_NOT_FOUND_MESSAGE, id)));
    }
}
