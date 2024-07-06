package org.project4.back_end.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private int productId;
    private String productName;
    private String productDescribe;
    private int productView;
    private String productImages;
    private BigDecimal productPrice;
    private int productQTY;
    private boolean productStatus;
    private CategoryDTO productCategory;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
    }

    public int getProductView() {
        return productView;
    }

    public void setProductView(int productView) {
        this.productView = productView;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQTY() {
        return productQTY;
    }

    public void setProductQTY(int productQTY) {
        this.productQTY = productQTY;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }


    public CategoryDTO getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(CategoryDTO productCategory) {
        this.productCategory = productCategory;
    }
}
