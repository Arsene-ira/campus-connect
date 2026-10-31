package com.example.campus_connect.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastname);
    
}
