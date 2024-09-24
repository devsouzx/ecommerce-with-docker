package com.devsouzx.ecommerce_with_docker.mapper;

import com.devsouzx.ecommerce_with_docker.dto.CartDTO;
import com.devsouzx.ecommerce_with_docker.dto.CartItemDTO;
import com.devsouzx.ecommerce_with_docker.model.Cart;
import com.devsouzx.ecommerce_with_docker.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "userId", source = "user.id")
    CartDTO toDTO(Cart Cart);

    @Mapping(target="user.id", source = "userId")
    Cart toEntity(CartDTO cartDTO);

    @Mapping(target="productId", source="product.id")
    CartItemDTO toDTO(CartItem cartItem);

    @Mapping(target="product.id", source="productId")
    CartItem toEntity(CartItemDTO cartItemDTO);
}
