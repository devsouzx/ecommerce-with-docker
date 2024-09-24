package com.devsouzx.ecommerce_with_docker.repositories;

import com.devsouzx.ecommerce_with_docker.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(Long productId);
}