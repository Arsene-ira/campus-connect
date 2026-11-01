package com.example.campus_connect.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.campus_connect.Model.OrderStatus;
import com.example.campus_connect.Model.ServiceOrder;


public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {
    //b2
    List<ServiceOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    //b3
    List<ServiceOrder> findByOrderDateBetweenOrderByOrderDateAsc(LocalDate start, LocalDate end);
    //d1
    boolean existsByVendorIdAndOrderDateAndStatusNot(UUID vendorId, LocalDate orderDate, OrderStatus status);
    //part f1
    @Query("SELECT o.status, COUNT(o) FROM ServiceOrder o GROUP BY o.status")
    List<Object[]> countByStatus();
    //f2
    @Modifying
    @Query("UPDATE ServiceOrder o SET o.status = :declined " +
       "WHERE o.vendor.id = :vendorId AND o.orderDate = :date " +
       "AND o.status <> :delivered")
    int cancelDay(@Param("vendorId") UUID vendorId,
              @Param("date") LocalDate date,
              @Param("declined") OrderStatus declined,
              @Param("delivered") OrderStatus delivered);

}
