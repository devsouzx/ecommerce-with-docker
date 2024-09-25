package com.devsouzx.ecommerce_with_docker.service;

import com.devsouzx.ecommerce_with_docker.dto.CartDTO;
import com.devsouzx.ecommerce_with_docker.exception.InsufficientStockException;
import com.devsouzx.ecommerce_with_docker.exception.ResourceNotFoundException;
import com.devsouzx.ecommerce_with_docker.mapper.CartMapper;
import com.devsouzx.ecommerce_with_docker.model.Cart;
import com.devsouzx.ecommerce_with_docker.model.CartItem;
import com.devsouzx.ecommerce_with_docker.model.Product;
import com.devsouzx.ecommerce_with_docker.model.User;
import com.devsouzx.ecommerce_with_docker.repositories.CartRepository;
import com.devsouzx.ecommerce_with_docker.repositories.ProductRepository;
import com.devsouzx.ecommerce_with_docker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    public CartDTO addToCart(Long userId, Long productId, Integer quantity){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getQuantity() < quantity){
            throw new InsufficientStockException("Not enough available");
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(null, user, new ArrayList<>()));
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        } else {
            CartItem cartItem = new CartItem(null, cart, product, quantity);
            cart.getItems().add(cartItem);
        }
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    public CartDTO getCart(Long userId){
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return cartMapper.toDTO(cart);
    }

    public void clearCart(Long userId){
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
