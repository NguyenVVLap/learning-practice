package com.example.OrderService.repository;

import com.example.OrderService.entity.OrderDetail;
import com.example.OrderService.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {

}
