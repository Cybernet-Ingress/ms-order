package com.java.msorder.dao.entity;


import com.java.msorder.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    LocalDateTime updateAt;
    @Column(updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;
    @Enumerated(STRING)
    OrderStatus status;
    BigDecimal orderAmount;
    @OneToMany(mappedBy = "orderEntity", cascade = ALL, fetch = LAZY)
    @ToStringExclude
    List<OrderItemEntity> orderItems;
    Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity orderEntity = (OrderEntity) o;
        return Objects.equals(id, orderEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
