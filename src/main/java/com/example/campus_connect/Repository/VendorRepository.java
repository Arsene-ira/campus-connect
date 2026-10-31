package com.example.campus_connect.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.campus_connect.Model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    //c1
    @Query("SELECT DISTINCT v FROM Vendor v JOIN v.serviceTypes st " +
       "WHERE LOWER(st.name) LIKE LOWER(CONCAT('%', :name, '%'))")
List<Vendor> searchByServiceTypeName(@Param("name") String name);
    
}

