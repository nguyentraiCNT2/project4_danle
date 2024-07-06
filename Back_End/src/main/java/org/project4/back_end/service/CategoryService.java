package org.project4.back_end.service;

import org.project4.back_end.Entity.CategoryEntity;
import org.project4.back_end.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getAllCategories(Pageable pageable);
    public int totalItem();

    public List<CategoryDTO> getByCategoryName(String categoryName,Pageable pageable);

    public CategoryDTO getCategoryById(Integer categoryId);

    public void createCategory(CategoryDTO categoryEntity);

    public void updateCategory(CategoryDTO categoryEntity);

    public void deleteCategory(Integer categoryId);
}
