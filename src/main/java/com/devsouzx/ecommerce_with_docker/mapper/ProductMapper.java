package com.devsouzx.ecommerce_with_docker.mapper;

import com.devsouzx.ecommerce_with_docker.dto.CommentDTO;
import com.devsouzx.ecommerce_with_docker.dto.ProductDTO;
import com.devsouzx.ecommerce_with_docker.model.Comment;
import com.devsouzx.ecommerce_with_docker.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "image", source = "image")
    ProductDTO toDTO(Product product);

    @Mapping(target = "image", source = "image")
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "userId",source = "user.id")
    CommentDTO toDTO(Comment comment);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "product", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
}
