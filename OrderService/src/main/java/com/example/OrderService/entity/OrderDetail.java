package com.example.OrderService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
@IdClass(OrderDetailPK.class)
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @Column(name = "product_id")
    private Long productId;

    private int quantity;

    private double price;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}
