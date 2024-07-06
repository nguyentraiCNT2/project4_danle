package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.CategoryEntity;
import org.project4.back_end.dto.CategoryDTO;
import org.project4.back_end.repository.CategoryRepository;
import org.project4.back_end.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CategoryDTO> getAllCategories(Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable).getContent();
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO DTO = modelMapper.map(item,CategoryDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public List<CategoryDTO> getByCategoryName(String categoryName, Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findByCategoryName(categoryName,pageable);
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO DTO = modelMapper.map(item,CategoryDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        try {
            CategoryEntity category = categoryRepository.findByCategoryId(categoryId);
            if (category == null) {
                throw new EntityNotFoundException("Category not found");
            }
            return modelMapper.map(category, CategoryDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void createCategory(CategoryDTO categoryEntity) {
        if ( categoryEntity != null) {
            CategoryEntity category = modelMapper.map(categoryEntity, CategoryEntity.class);
            if (category != null) {
                categoryRepository.save(category);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateCategory(CategoryDTO categoryEntity) {
        if (categoryEntity != null) {
            CategoryEntity category = categoryRepository.findByCategoryId(categoryEntity.getCategoryId());
                    modelMapper.map(categoryEntity,category);
            if (category != null) {
                categoryRepository.save(category);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteByCategoryId(categoryId);
    }
}
