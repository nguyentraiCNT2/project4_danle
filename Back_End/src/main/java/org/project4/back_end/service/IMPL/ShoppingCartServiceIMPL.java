package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.RoleEntity;
import org.project4.back_end.Entity.ShoppingCartEntity;
import org.project4.back_end.Entity.UserEntity;
import org.project4.back_end.dto.RoleDTO;
import org.project4.back_end.dto.ShoppingCartDTO;
import org.project4.back_end.repository.ProductRepository;
import org.project4.back_end.repository.ShoppingCartRepository;
import org.project4.back_end.repository.UsersRepository;
import org.project4.back_end.service.ProductService;
import org.project4.back_end.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceIMPL implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public int totalItem() {
        return (int) shoppingCartRepository.count();
    }

    @Override
    public List<ShoppingCartDTO> getAllShoppingCarts(Pageable pageable) {
        List<ShoppingCartDTO> results = new ArrayList<>();
        List<ShoppingCartEntity> shoppingCartEntities = shoppingCartRepository.findAll(pageable).getContent();
        for (ShoppingCartEntity item : shoppingCartEntities
        ) {
            ShoppingCartDTO DTO = modelMapper.map(item, ShoppingCartDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<ShoppingCartDTO> getByUserId(Integer userId, Pageable pageable) {
        List<ShoppingCartDTO> results = new ArrayList<>();
        UserEntity userEntity = usersRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<ShoppingCartEntity> shoppingCartEntities = shoppingCartRepository.findByCartUser(userEntity,pageable);
        for (ShoppingCartEntity item : shoppingCartEntities
        ) {
            ShoppingCartDTO DTO = modelMapper.map(item, ShoppingCartDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public void updateShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(shoppingCartDTO.getCartId()).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(shoppingCartDTO, shoppingCartEntity);
        BigDecimal cartToTal = shoppingCartDTO.getCartProduct().getProductPrice().multiply(new BigDecimal(shoppingCartEntity.getCartQTY()));
        shoppingCartEntity.setCartToTal(cartToTal);
        shoppingCartEntity.setCartUser(usersRepository.findById(shoppingCartDTO.getCartUser().getUserId()).orElseThrow(EntityNotFoundException::new));
        shoppingCartEntity.setCartProduct(productRepository.findByProductId(shoppingCartDTO.getCartProduct().getProductId()));
        shoppingCartRepository.save(shoppingCartEntity);
    }

    @Override
    public void createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartEntity shoppingCartEntity = modelMapper.map(shoppingCartDTO, ShoppingCartEntity.class);
        shoppingCartEntity.setCartQTY(1);
        BigDecimal cartToTal = shoppingCartDTO.getCartProduct().getProductPrice().multiply(new BigDecimal(shoppingCartEntity.getCartQTY()));
        shoppingCartEntity.setCartToTal(cartToTal);
        shoppingCartEntity.setCartUser(usersRepository.findById(shoppingCartDTO.getCartUser().getUserId()).orElseThrow(EntityNotFoundException::new));
        shoppingCartEntity.setCartProduct(productRepository.findByProductId(shoppingCartDTO.getCartProduct().getProductId()));
        shoppingCartRepository.save(shoppingCartEntity);
    }

    @Override
    public void deleteShoppingCart(int id) {
        shoppingCartRepository.deleteByCartId(id);
    }


}
