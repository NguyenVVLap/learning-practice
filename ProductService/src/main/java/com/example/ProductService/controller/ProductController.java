package com.example.ProductService.controller;

import com.example.ProductService.dto.ResponseProductDTO;
import com.example.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/products"})
    public ResponseEntity<ResponseProductDTO> getProductsWithPageSortByCategory(@RequestParam long categoryId, @RequestParam("priceMin") double minPrice, @RequestParam("priceMax") double maxPrice, @RequestParam String sortBy, @RequestParam("page") int numbOfPage, @RequestParam("size") int numOfProductInPage) {
        return ResponseEntity.ok(this.productService.getProductsWithPageSortByCategory(categoryId, minPrice, maxPrice, sortBy, numbOfPage, numOfProductInPage));
    }
}
