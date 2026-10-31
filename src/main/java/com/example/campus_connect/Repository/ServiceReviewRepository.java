package com.example.campus_connect.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.campus_connect.Model.ServiceOrder;
import com.example.campus_connect.Model.ServiceReview;

public interface ServiceReviewRepository extends JpaRepository<ServiceReview, UUID> {

    // E2 (JPQL)
    @Query("SELECT r FROM ServiceReview r " +
           "WHERE r.serviceOrder.student.studentNumber = :studentNumber " +
           "ORDER BY r.reviewDate DESC")
    List<ServiceReview> historyOfStudent(@Param("studentNumber") String studentNumber);

    // E3 (DERIVED)
    boolean existsByServiceOrder(ServiceOrder serviceOrder);
}