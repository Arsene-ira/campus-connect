package com.example.campus_connect.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campus_connect.Model.Kiosk;
import org.springframework.data.jpa.repository.Query;

public interface KioskRepository extends JpaRepository<Kiosk, UUID> {
    //c2
    @Query("SELECT k FROM Kiosk k " +
       "WHERE NOT EXISTS (SELECT v FROM Vendor v WHERE v.kiosk = k) " +
       "ORDER BY k.kioskNumber")
List<Kiosk> findFreeKiosks();
}
