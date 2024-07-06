package org.project4.back_end.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name", columnDefinition = "Nvarchar(255)")
    private String productName;
    @Column(name = "product_describe",columnDefinition = "Nvarchar(255)")
    private String productDescribe;
    @Column(name = "product_view")
    private int productView;
    @Column(name = "product_images")
    private String productImages;
    @Column(name = "product_price")
    private BigDecimal productPrice;
    @Column(name = "product_qty")
    private int productQTY;
    @Column(name = "product_status")
    private boolean productStatus;
    @OneToOne
    @JoinColumn(name = "product_category")
    private CategoryEntity productCategory;

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

    public CategoryEntity getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(CategoryEntity productCategory) {
        this.productCategory = productCategory;
    }
}
