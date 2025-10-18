package com.example.ProductService.controller;

import com.example.ProductService.dto.ResponseProductDTO;
import com.example.ProductService.entity.Product;
import com.example.ProductService.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<ResponseProductDTO> getProductsWithPageSortByCategory(@RequestParam long categoryId, @RequestParam("priceMin") double minPrice, @RequestParam("priceMax") double maxPrice, @RequestParam String sortBy, @RequestParam("page") int numbOfPage, @RequestParam("size") int numOfProductInPage) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(5, TimeUnit.SECONDS))
                .body(this.productService.getProductsWithPageSortByCategory(categoryId, minPrice, maxPrice, sortBy, numbOfPage, numOfProductInPage));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Product> getProductsWithPageSortByCategory(@PathVariable("id") long id) {
        Product result = this.productService.getProductById(id);
        if (result.getId() == null) {
            return ResponseEntity.notFound().build();
        }
//        Default etag value - must create bean shallowEtagHeaderFilter
        return ResponseEntity.ok().body(result);
//        Custom etag value - no more config required
//        return ResponseEntity.ok().eTag(result.getId().toString()).body(result);
    }

    @PutMapping()
    public ResponseEntity<Product> updateNameProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.updateNameProduct(product));
    }
}
