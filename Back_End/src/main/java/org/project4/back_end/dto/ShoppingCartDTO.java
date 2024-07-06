package org.project4.back_end.dto;

import java.math.BigDecimal;

public class ShoppingCartDTO {
    private int cartId;
    private int cartQTY;
    private BigDecimal cartToTal;
    private UserDTO cartUser;
    private ProductDTO cartProduct;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCartQTY() {
        return cartQTY;
    }

    public void setCartQTY(int cartQTY) {
        this.cartQTY = cartQTY;
    }

    public BigDecimal getCartToTal() {
        return cartToTal;
    }

    public void setCartToTal(BigDecimal cartToTal) {
        this.cartToTal = cartToTal;
    }

    public UserDTO getCartUser() {
        return cartUser;
    }

    public void setCartUser(UserDTO cartUser) {
        this.cartUser = cartUser;
    }

    public ProductDTO getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(ProductDTO cartProduct) {
        this.cartProduct = cartProduct;
    }
}
