package org.project4.back_end.repository;

import org.project4.back_end.Entity.OrderDetailEntity;
import org.project4.back_end.Entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    List<OrderDetailEntity> findByOrder(OrderEntity orderId, Pageable pageable);

    OrderDetailEntity findByOrderDetailId(Integer orderDetailId);

    void deleteByOrderDetailId(Integer orderDetailId);
}
