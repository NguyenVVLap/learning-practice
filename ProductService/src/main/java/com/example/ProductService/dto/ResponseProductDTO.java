package com.example.ProductService.dto;

import com.example.ProductService.entity.Product;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ResponseProductDTO {
    private int totalPages;
    private long totalItems;
    private int currentPage;
    private List<Product> listProduct;
}
