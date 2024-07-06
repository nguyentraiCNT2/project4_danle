package org.project4.back_end.repository;

import org.project4.back_end.Entity.OrderEntity;
import org.project4.back_end.Entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByOrderUser(UserEntity  userEntity, Pageable pageable);
    List<OrderEntity> findByDeliveryStatus(String  deliveryStatus, Pageable pageable);
    List<OrderEntity> findByOrderStatus(String  orderstatus, Pageable pageable);
    List<OrderEntity> findByPayMentStatus(String  paymentStatus, Pageable pageable);
    OrderEntity findByOrderId(Integer orderId);

    void deleteByOrderId(Integer orderId);
}
