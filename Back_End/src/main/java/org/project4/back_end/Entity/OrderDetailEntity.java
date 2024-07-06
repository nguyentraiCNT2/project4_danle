package org.project4.back_end.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "orderDetail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int orderDetailId;
    @Column(name = "order_detail_qty")
    private int orderDetailQTY;
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

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


    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
