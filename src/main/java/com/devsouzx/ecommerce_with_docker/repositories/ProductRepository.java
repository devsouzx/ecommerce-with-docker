package com.devsouzx.ecommerce_with_docker.repositories;

import com.devsouzx.ecommerce_with_docker.dto.ProductListDTO;
import com.devsouzx.ecommerce_with_docker.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.devsouzx.ecommerce_with_docker.dto.ProductListDTO(p.id, p.name, p.description, p.price, p.quantity, p.image) FROM Product p")
    Page<ProductListDTO> findAllWithoutComments(Pageable pageable);
}
