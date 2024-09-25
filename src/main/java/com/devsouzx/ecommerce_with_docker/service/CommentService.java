package com.devsouzx.ecommerce_with_docker.service;

import com.devsouzx.ecommerce_with_docker.dto.CommentDTO;
import com.devsouzx.ecommerce_with_docker.exception.ResourceNotFoundException;
import com.devsouzx.ecommerce_with_docker.mapper.CommentMapper;
import com.devsouzx.ecommerce_with_docker.model.Comment;
import com.devsouzx.ecommerce_with_docker.model.Product;
import com.devsouzx.ecommerce_with_docker.model.User;
import com.devsouzx.ecommerce_with_docker.repositories.CommentRepository;
import com.devsouzx.ecommerce_with_docker.repositories.ProductRepository;
import com.devsouzx.ecommerce_with_docker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentDTO addComment(Long productId, Long userId, CommentDTO commentDTO){
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setProduct(product);
        comment.setUser(user);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);
    }

    public List<CommentDTO> getCommentsByProduct(Long productId){
        List<Comment> comments = commentRepository.findByProductId(productId);
        return comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
}