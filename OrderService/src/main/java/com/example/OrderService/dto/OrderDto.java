package com.example.OrderService.dto;

import com.example.OrderService.entity.OrderDetail;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderDetail> listOrderDetail;
    private double totalPrice;
}
