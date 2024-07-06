package org.project4.back_end.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shoppingCart")
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "cart_qty")
    private int cartQTY;
    @Column(name = "cart_to_tal")
    private BigDecimal cartToTal;
    @OneToOne
    @JoinColumn(name = "cart_user")
    private UserEntity cartUser;
    @OneToOne
    @JoinColumn(name = "cart_product")
    private ProductEntity cartProduct;

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
    public UserEntity getCartUser() {
        return cartUser;
    }
    public void setCartUser(UserEntity cartUser) {
        this.cartUser = cartUser;
    }
    public ProductEntity getCartProduct() {
        return cartProduct;
    }
    public void setCartProduct(ProductEntity cartProduct) {
        this.cartProduct = cartProduct;
    }
}
