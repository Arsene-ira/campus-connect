package com.example.campus_connect.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, UUID> {
    
}

