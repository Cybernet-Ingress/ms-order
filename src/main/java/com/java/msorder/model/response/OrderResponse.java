package com.java.msorder.model.response;

import com.java.msorder.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level =PRIVATE)
public class OrderResponse {

     LocalDateTime createdAt;
     OrderStatus orderStatus;
     BigDecimal orderAmount;
     List<OrderItemResponse> orderItemResponses;


}
