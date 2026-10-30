package com.example.campus_connect.Model;

import jakarta.persistence.*;
import java.util.UUID;
@Entity 
@Table (name = "kiosk")
public class Kiosk {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int kioskNumber;
    private String name;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getKioskNumber() {
        return kioskNumber;
    }
    public void setKioskNumber(int kioskNumber) {
        this.kioskNumber = kioskNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Kiosk() {
    }
    
    
    
}
