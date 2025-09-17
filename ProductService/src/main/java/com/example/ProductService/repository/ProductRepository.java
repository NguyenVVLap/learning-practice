package com.example.ProductService.repository;

import com.example.ProductService.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true,
            value = "select * from product where category_id = :categoryId and price >= :minPrice and price <= :maxPrice")
    Page<Product> findAllByCategory(@Param("categoryId") long categoryId, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, Pageable pageable);
}
