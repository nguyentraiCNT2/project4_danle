package org.project4.back_end.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "order_date_add")
    private Date orderDateAdd;
    @Column(name = "order_date_out")
    private Date orderDateOut;
    @Column(name = "delivery_status", columnDefinition = "Nvarchar(255)")
    private String deliveryStatus;
    @Column(name = "order_status", columnDefinition = "Nvarchar(255)")
    private String orderStatus;
    @Column(name = "pay_ment_status", columnDefinition = "Nvarchar(255)")
    private String payMentStatus;
    @OneToOne
    @JoinColumn(name = "order_user")
    private UserEntity orderUser;
    @Column(name = "order_address", columnDefinition = "Nvarchar(255)")
    private String orderAddress;
    @Column(name = "order_phone", columnDefinition = "Nvarchar(255)")
    private String phone;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDateAdd() {
        return orderDateAdd;
    }

    public void setOrderDateAdd(Date orderDateAdd) {
        this.orderDateAdd = orderDateAdd;
    }

    public Date getOrderDateOut() {
        return orderDateOut;
    }

    public void setOrderDateOut(Date orderDateOut) {
        this.orderDateOut = orderDateOut;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayMentStatus() {
        return payMentStatus;
    }

    public void setPayMentStatus(String payMentStatus) {
        this.payMentStatus = payMentStatus;
    }

    public UserEntity getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(UserEntity orderUser) {
        this.orderUser = orderUser;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
