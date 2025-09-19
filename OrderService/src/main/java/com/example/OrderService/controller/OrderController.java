package com.example.OrderService.controller;

import com.example.OrderService.dto.OrderDto;
import com.example.OrderService.entity.Order;
import com.example.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/v1/getAll")
    public ResponseEntity<List<Order>> findAllOrdersV1() {
        return ResponseEntity.ok(orderService.getAllOrderV1());
    }

    @GetMapping("/v2/getAll")
    public ResponseEntity<List<OrderDto>> findAllOrdersV2() {
        return ResponseEntity.ok(orderService.getAllOrderV2());
    }
}
