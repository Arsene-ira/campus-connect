package com.example.campus_connect.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {
    
}
