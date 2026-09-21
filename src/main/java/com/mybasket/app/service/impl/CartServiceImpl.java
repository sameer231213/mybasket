package com.mybasket.app.service.impl;

import com.mybasket.app.dto.CartDto;
import com.mybasket.app.entity.Cart;
import com.mybasket.app.repository.CartRepository;
import com.mybasket.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  CartServiceImpl implements CartService {


    private final CartRepository cartRepository;


    @Override
    public CartDto getCart(Integer userId) {

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return convertToDto(cart);
    }

    private CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        // Cart aur CartDto ke baaki fields bhi set karna

        return cartDto;
    }


    @Override
    public CartDto addToCart(Integer userId, Long productId, int quantity) {

        return null;
    }
}