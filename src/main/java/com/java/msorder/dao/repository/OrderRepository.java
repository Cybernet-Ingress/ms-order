package com.java.msorder.dao.repository;

import com.java.msorder.dao.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity,Long> {

    List<OrderEntity> findAllOrderByUserId(Long id);
}
