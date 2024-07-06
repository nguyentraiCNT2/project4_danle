package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.CategoryEntity;
import org.project4.back_end.Entity.ProductEntity;
import org.project4.back_end.dto.ProductDTO;
import org.project4.back_end.repository.CategoryRepository;
import org.project4.back_end.repository.ProductRepository;
import org.project4.back_end.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceIMPL implements ProductService {
    @Value("D:/GuardianNestShopcode/templates/public/images/")
    // Đường dẫn để lưu ảnh, có thể đặt trong file properties/application.yml
    private String imageSavePath;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }

    @Override
    public List<ProductDTO> getAllProducts(Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll(pageable).getContent();
        for (ProductEntity item: productEntities
        ) {
            ProductDTO DTO = modelMapper.map(item,ProductDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<ProductDTO> getByProductName(String productName, Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findByProductName(productName,pageable);
        for (ProductEntity item: productEntities
        ) {
            ProductDTO DTO = modelMapper.map(item,ProductDTO.class);
            results.add(DTO);
        }
        return results;
    }


    @Override
    public List<ProductDTO> getByProductCategory(int productCategory, Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(productCategory);
        List<ProductEntity> productEntities = productRepository.findByProductCategory(categoryEntity,pageable);
        for (ProductEntity item: productEntities
        ) {
            ProductDTO DTO = modelMapper.map(item,ProductDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<ProductDTO> getByProductStatus(boolean productStatus, Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findByProductStatus(productStatus,pageable);
        for (ProductEntity item: productEntities
        ) {
            ProductDTO DTO = modelMapper.map(item,ProductDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        try {
            ProductEntity product = productRepository.findByProductId(id);
            if (product == null) {
                throw new EntityNotFoundException("Category not found");
            }
            return modelMapper.map(product, ProductDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void saveProduct(ProductDTO productDTO, MultipartFile file) throws IOException {
        if (productDTO != null) {
            ProductEntity product = modelMapper.map(productDTO, ProductEntity.class);
            CategoryEntity categoryEntity = categoryRepository.findByCategoryId(productDTO.getProductCategory().getCategoryId());

                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String filePath = imageSavePath + filename;
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                product.setProductImages(filename);
                product.setProductCategory(categoryEntity);
                productRepository.save(product);
            }
        }


    @Override
    public void updateProduct(ProductDTO productDTO, MultipartFile file) throws IOException{
    ProductEntity product = productRepository.findByProductId(productDTO.getProductId());
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = imageSavePath + filename;
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

    if (productDTO.getProductCategory() != null) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(productDTO.getProductCategory().getCategoryId());
        product.setProductCategory(categoryEntity);
    }

        if (productDTO.getProductImages() != null) {
            product.setProductImages(productDTO.getProductImages());
        }
        modelMapper.map(productDTO, product);
    product.setProductImages(filename);
    productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
    productRepository.deleteByProductId(id);
    }
}
