package com.example.campus_connect.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastname);
    //ce4
    @Query("SELECT DISTINCT o.student FROM ServiceOrder o WHERE o.vendor.id = :vendorId")
List<Student> studentsOfVendor(@Param("vendorId") UUID vendorId);
    
}
