package org.project4.back_end.Controller.admin;

import jakarta.transaction.Transactional;
import org.project4.back_end.dto.CategoryDTO;
import org.project4.back_end.dto.ProductDTO;
import org.project4.back_end.output.ProductOutPut;
import org.project4.back_end.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/product")
public class ProductControlnler {
    @Autowired
    private ProductService productService;
    @GetMapping("/getall")
    public ProductOutPut getAllUsers(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        ProductOutPut result = new ProductOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(productService.getAllProducts(pageable));
        result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }

    @GetMapping("/getbyname/{name}")
    public ProductOutPut getRoleByRolename(@PathVariable String name, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        ProductOutPut result = new ProductOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(productService.getByProductName(name,pageable));
        result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getByRoleid(@PathVariable int  id){
        try {
            ProductDTO product = productService.getProductById(id);
            if (product==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/create")
    public ResponseEntity<String> createrole(@RequestParam("productName") String productName
            , @RequestParam("productPrice") BigDecimal productPrice
            , @RequestParam("productDescription") String productDescription
            , @RequestParam("productQuantity") int productQuantity
            , @RequestParam("productView") int productView
            , @RequestParam("categoryId") int categoryId
            , @RequestParam("file") MultipartFile file) {
        try {
            CategoryDTO  categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(categoryId);
            ProductDTO dto = new ProductDTO();
            dto.setProductName(productName);
            dto.setProductPrice(productPrice);
            dto.setProductDescribe(productDescription);
            dto.setProductQTY(productQuantity);
            dto.setProductView(productView);
            dto.setProductCategory(categoryDTO);
            productService.saveProduct(dto,file);
            return new ResponseEntity<>("more success " + dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<String> updateRole(@PathVariable int id, @RequestParam("productName") String productName
            , @RequestParam("productPrice") BigDecimal productPrice
            , @RequestParam("productDescription") String productDescription
            , @RequestParam("productQuantity") int productQuantity
            , @RequestParam("productView") int productView
            , @RequestParam("categoryId") int categoryId
            , @RequestParam("file") MultipartFile file){
        try {
            CategoryDTO  categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(categoryId);
            ProductDTO dto = new ProductDTO();
            dto.setProductName(productName);
            dto.setProductPrice(productPrice);
            dto.setProductDescribe(productDescription);
            dto.setProductQTY(productQuantity);
            dto.setProductView(productView);
            dto.setProductCategory(categoryDTO);
            dto.setProductId(id);
            productService.updateProduct(dto,file);
            return new ResponseEntity<>(dto+"Cập nhật quyền người dùng thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Xóa  thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
