package com.java.msorder.model.request;

import com.java.msorder.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCriteria {

    private OrderStatus status;
    private BigDecimal orderAmount;
}
