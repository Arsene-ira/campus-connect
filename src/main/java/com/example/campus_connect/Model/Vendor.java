package com.example.campus_connect.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;


@Entity 
@Table(name = "vendor")
public class Vendor {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String ownerName ;
    private String businessName;
    private String phone;
    
    @ManyToOne 
    @JoinColumn (name= "kiosk_id")
    private Kiosk kiosk;

    @ManyToMany 
    @JoinTable(
        name = "vendor_service_type",
        joinColumns = @JoinColumn(name = "vendor_id"),
        inverseJoinColumns = @JoinColumn(name = "service_type_id")
    )

    private List<ServiceType> serviceTypes = new ArrayList<>();

    public Vendor() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Kiosk getKiosk() {
        return kiosk;
    }

    public void setKiosk(Kiosk kiosk) {
        this.kiosk = kiosk;
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }
    


    
}
