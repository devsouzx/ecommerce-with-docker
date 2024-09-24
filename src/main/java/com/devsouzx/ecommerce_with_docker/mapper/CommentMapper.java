package com.devsouzx.ecommerce_with_docker.mapper;

import com.devsouzx.ecommerce_with_docker.dto.CommentDTO;
import com.devsouzx.ecommerce_with_docker.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "userId",source = "user.id")
    CommentDTO toDTO(Comment comment);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "product", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
}
