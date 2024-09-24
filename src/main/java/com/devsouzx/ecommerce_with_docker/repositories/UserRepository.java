package com.devsouzx.ecommerce_with_docker.repositories;

import com.devsouzx.ecommerce_with_docker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
