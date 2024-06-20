package com.java.msorder.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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
     BigDecimal orderAmount;
     List<OrderItemResponse> orderItemResponses;


}
