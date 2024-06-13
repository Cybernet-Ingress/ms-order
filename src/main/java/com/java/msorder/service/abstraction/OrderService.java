package com.java.msorder.service.abstraction;

import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.model.enums.OrderStatus;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

   void createOrder(Long id, OrderRequest request);

   List<OrderResponse> getOrdersByUserId(Long id);

   void deleteOrderById(Long id);

  // void updateOrderStatus(Long id, OrderStatus status);


}
