package org.project4.back_end.repository;

import org.project4.back_end.Entity.ShoppingCartEntity;
import org.project4.back_end.Entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {
    List<ShoppingCartEntity> findByCartUser(UserEntity shoppingCartUser, Pageable pageable);

    ShoppingCartEntity findByCartId(Integer shoppingCartId);

    void deleteByCartId(Integer shoppingCartId);
}
