package com.java.msorder.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@Builder
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    LocalDateTime updateAt;
    @Column(updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;
    String name;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal totalPrice;
    Long productId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    @ToStringExclude
    OrderEntity orderEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity orderItemEntity = (OrderItemEntity) o;
        return Objects.equals(id, orderItemEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
