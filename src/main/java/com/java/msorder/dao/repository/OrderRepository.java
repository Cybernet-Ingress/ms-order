package com.java.msorder.dao.repository;

import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findAllOrderByUserId(Long id);
    @Query("SELECT o FROM OrderEntity o WHERE o.id = :id AND o.status <> :status")
    Optional<OrderEntity> findByIdAndStatusNotCanceled(Long id,OrderStatus status);


}
