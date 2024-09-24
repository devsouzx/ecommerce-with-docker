package com.devsouzx.ecommerce_with_docker.repositories;

import com.devsouzx.ecommerce_with_docker.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}