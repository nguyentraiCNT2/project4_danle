package org.project4.back_end.service;

import org.project4.back_end.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public int totalItem();

    public List<ProductDTO> getAllProducts(Pageable pageable);

    public List<ProductDTO> getByProductName(String productName, Pageable pageable);
    List<ProductDTO> getByProductCategory(int productCategory, Pageable pageable);
    List<ProductDTO> getByProductStatus(boolean productStatus, Pageable pageable);

    public ProductDTO getProductById(Integer id);

    public void saveProduct(ProductDTO productDTO , MultipartFile file) throws IOException;

    public void updateProduct(ProductDTO productDTO, MultipartFile file) throws IOException;

    public void deleteProduct(Integer id);
}
