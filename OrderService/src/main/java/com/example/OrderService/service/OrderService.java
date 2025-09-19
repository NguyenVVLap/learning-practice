package com.example.OrderService.service;

import com.example.OrderService.dto.OrderDto;
import com.example.OrderService.entity.Order;
import com.example.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrderV1() {
        return orderRepository.findAll();
    }

    public List<OrderDto> getAllOrderV2() {
        List<Order> listOrder = orderRepository.findAll();
        List<OrderDto> listOrderDto = new ArrayList<>();
        for (Order order : listOrder) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setListOrderDetail(order.getListOrderDetail());
            orderDto.setTotalPrice(order.totalPrice());
            listOrderDto.add(orderDto);
        }
        return listOrderDto;
    }
}
