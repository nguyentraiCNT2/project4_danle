package org.project4.back_end.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderDTO {
    private int orderId;
    private Date orderDateAdd;
    private Date orderDateOut;
    private String deliveryStatus;
    private String orderStatus;
    private String payMentStatus;
    private UserDTO orderUser;
    private String orderAddress;
    private String phone;
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

    public UserDTO getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(UserDTO orderUser) {
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
