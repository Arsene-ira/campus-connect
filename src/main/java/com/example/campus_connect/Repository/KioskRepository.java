package com.example.campus_connect.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.campus_connect.Model.Kiosk;

public interface KioskRepository extends JpaRepository<Kiosk, UUID> {
    //c2
    @Query("SELECT k FROM Kiosk k " +
       "WHERE NOT EXISTS (SELECT v FROM Vendor v WHERE v.kiosk = k) " +
       "ORDER BY k.kioskNumber")
List<Kiosk> findFreeKiosks();
    //d4
    Optional<Kiosk> findByKioskNumber(int kioskNumber);
}
