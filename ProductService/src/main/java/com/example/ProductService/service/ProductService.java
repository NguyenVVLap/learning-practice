package com.example.ProductService.service;

import com.example.ProductService.dto.ResponseProductDTO;
import com.example.ProductService.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseProductDTO getProductsWithPageSortByCategory(long categoryId, double minPrice, double maxPrice, String sortBy, int numbOfPage, int numOfProductInPage) {
        Pageable pageable = PageRequest.of(numbOfPage, numOfProductInPage, Sort.by(new String[]{sortBy}).descending());
        Page<Product> pageProduct = this.productRepository.findAllByCategory(categoryId, minPrice, maxPrice, pageable);
        return ResponseProductDTO.builder().totalPages(pageProduct.getTotalPages()).totalItems(pageProduct.getTotalElements()).currentPage(pageProduct.getNumber()).listProduct(pageProduct.getContent()).build();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).isPresent() ? productRepository.findById(id).get() : new Product();
    }

    public Product updateNameProduct(Product product) {
        return productRepository.save(product);
    }
}
