package org.project4.back_end.repository;

import org.project4.back_end.Entity.CategoryEntity;
import org.project4.back_end.Entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("select p from ProductEntity p where p.productName like :productName")
    List<ProductEntity> findByProductName(@Param("productName") String productName, Pageable pageable);

    List<ProductEntity> findByProductCategory(CategoryEntity categoryEntity, Pageable pageable);

    List<ProductEntity> findByProductStatus(Boolean status, Pageable pageable);

    ProductEntity findByProductId(Integer productId);

    void deleteByProductId(Integer productId);
}
