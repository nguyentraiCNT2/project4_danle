package org.project4.back_end.service;

import org.project4.back_end.dto.ShoppingCartDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShoppingCartService {
    public int totalItem();
    List<ShoppingCartDTO> getAllShoppingCarts(Pageable pageable);
    List<ShoppingCartDTO> getByUserId(Integer userId, Pageable pageable);
    public void updateShoppingCart(ShoppingCartDTO shoppingCartDTO);

    public void createShoppingCart(ShoppingCartDTO shoppingCartDTO);

    public void deleteShoppingCart(int id);
}
