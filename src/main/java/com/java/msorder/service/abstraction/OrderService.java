package com.java.msorder.service.abstraction;

import com.java.msorder.model.enums.OrderStatus;
import com.java.msorder.model.request.OrderCriteria;
import com.java.msorder.model.request.OrderRequest;
import com.java.msorder.model.request.PageCriteria;
import com.java.msorder.model.response.OrderItemResponse;
import com.java.msorder.model.response.OrderResponse;
import com.java.msorder.model.response.PageableResponse;

import java.util.List;

public interface OrderService {

   void createOrder(Long id, OrderRequest request);

   List<OrderResponse> getOrdersByUserId(Long id);

   PageableResponse<OrderResponse> getOrders(OrderCriteria orderCriteria, PageCriteria pageCriteria);

   List<OrderItemResponse> getOrderItemsByOrderId(Long id);
   void updateOrderStatus(Long id,OrderStatus orderStatus);

   void deleteOrderById(Long id);





}
