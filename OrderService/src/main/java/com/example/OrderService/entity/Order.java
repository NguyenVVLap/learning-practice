package com.example.OrderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> listOrderDetail;

    public double totalPrice() {
        double totalPrice = 0;
        for (OrderDetail orderDetail : this.listOrderDetail) {
            totalPrice += orderDetail.getPrice();
        }
        return totalPrice;
    }
}
