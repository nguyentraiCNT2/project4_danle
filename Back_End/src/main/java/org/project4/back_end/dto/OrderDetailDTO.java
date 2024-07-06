package org.project4.back_end.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private int orderDetailId;
    private int orderDetailQTY;
    private BigDecimal orderToTal;
    private OrderDTO order;
    private ProductDTO product;


    public int getOrderDetailId() {
        return orderDetailId;
    }
    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    public int getOrderDetailQTY() {
        return orderDetailQTY;
    }
    public void setOrderDetailQTY(int orderDetailQTY) {
        this.orderDetailQTY = orderDetailQTY;
    }
    public BigDecimal getOrderToTal() {
        return orderToTal;
    }
    public void setOrderToTal(BigDecimal orderToTal) {
        this.orderToTal = orderToTal;
    }
    public OrderDTO getOrder() {
        return order;
    }
    public void setOrder(OrderDTO order) {
        this.order = order;
    }
    public ProductDTO getProduct() {
        return product;
    }
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}
