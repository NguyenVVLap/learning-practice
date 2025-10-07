package com.example.OrderService.feignclient;

import com.example.OrderService.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ProductService", url = "http://localhost:8081")
public interface ProductClient {
    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    public ResponseEntity<Product> getProductsWithPageSortByCategory(@PathVariable("id") long id);
}
