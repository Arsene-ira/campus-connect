package com.example.campus_connect.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.OrderStatus;
import com.example.campus_connect.Model.ServiceOrder;


public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {
    //b2
    List<ServiceOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    //b3
    List<ServiceOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    //d1
    boolean existsByVendorIdAndOrderDateAndStatusNot(UUID vendorId, LocalDate orderDate, OrderStatus status);
}
