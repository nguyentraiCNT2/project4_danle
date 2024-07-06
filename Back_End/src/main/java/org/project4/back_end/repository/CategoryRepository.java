package org.project4.back_end.repository;

import org.project4.back_end.Entity.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query("select c from CategoryEntity c where c.categoryName like :categoryName")
    List<CategoryEntity> findByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);
    CategoryEntity findByCategoryId(Integer categoryId);
    void deleteByCategoryId(Integer categoryId);
}
