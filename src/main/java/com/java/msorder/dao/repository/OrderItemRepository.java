package com.java.msorder.dao.repository;

import com.java.msorder.dao.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity,Long> {

}
