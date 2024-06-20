package com.java.msorder.service.specification;

import com.java.msorder.dao.entity.OrderEntity;
import com.java.msorder.model.request.OrderCriteria;
import com.java.msorder.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
@Data
public class OrderSpecification implements Specification<OrderEntity> {

   private OrderCriteria orderCriteria;

    @Override
    public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates= PredicateUtil.builder()
                .addNullSafety(orderCriteria.getStatus(),status -> criteriaBuilder.equal(root.get("status"),status))
                .addNullSafety(orderCriteria.getOrderAmount(),amount->criteriaBuilder.equal(root.get("amount"),amount))
                .build();
        return criteriaBuilder.and(predicates);
    }
}
