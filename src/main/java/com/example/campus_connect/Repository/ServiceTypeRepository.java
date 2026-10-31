package com.example.campus_connect.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.ServiceType;
import org.springframework.data.jpa.repository.Query;


public interface ServiceTypeRepository extends JpaRepository<ServiceType, UUID> {

    @Query("SELECT st FROM ServiceType st WHERE st.vendors IS EMPTY")
List<ServiceType> findUnused();
    
}

