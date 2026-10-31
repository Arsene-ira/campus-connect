package com.example.campus_connect.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Model.Kiosk;
import com.example.campus_connect.Model.ServiceType;
import com.example.campus_connect.Model.Vendor;
import com.example.campus_connect.Repository.KioskRepository;
import com.example.campus_connect.Repository.ServiceTypeRepository;
import com.example.campus_connect.Repository.VendorRepository;


@Service 
public class VendorService {
    @Autowired 
    private VendorRepository repo;
    
    public String save(Vendor vendor){
        repo.save(vendor);
        return "Vendor saved successfully";
    }
    public List<Vendor> getAll(){
        return repo.findAll();
    }
    
    public Optional<Vendor> getById(UUID id){
            return repo.findById(id);
    }

    public String update(UUID id, Vendor newData){
        Optional<Vendor> found=repo.findById(id);
        if (found.isEmpty()){
            return "The Vendor with that id does not exist";
        }
        Vendor old= found.get();
        old.setOwnerName(newData.getOwnerName());
        old.setBusinessName(newData.getBusinessName());
        old.setPhone(newData.getPhone());
        old.setServiceTypes(newData.getServiceTypes());
        old.setKiosk(newData.getKiosk());
        repo.save(old);
        return "Vendor Updated successfully";
    }
    public String delete(UUID id){
        if (!repo.existsById(id)){
            return "The Vendor with that id does not exist";
        }
        repo.deleteById(id);
        return "Vendor deleted successfully";
    }
    //c1
    public List<Vendor> getByServiceType(String name) {
    return repo.searchByServiceTypeName(name);
}   
    //d3
    @Autowired
private ServiceTypeRepository serviceTypeRepo;

public String addServiceType(UUID vendorId, UUID serviceTypeId) {
    Optional<Vendor> foundVendor = repo.findById(vendorId);
    if (foundVendor.isEmpty()) {
        return "The vendor with that id does not exist";
    }
    Optional<ServiceType> foundType = serviceTypeRepo.findById(serviceTypeId);
    if (foundType.isEmpty()) {
        return "That service type does not exist";
    }
    Vendor vendor = foundVendor.get();
    ServiceType type = foundType.get();

    boolean alreadyHas = vendor.getServiceTypes().stream()
            .anyMatch(s -> s.getId().equals(serviceTypeId));
    if (alreadyHas) {
        return "The vendor already has that service type";
    }
    vendor.getServiceTypes().add(type);
    repo.save(vendor);
    return "Service type added to the vendor";
}
    //d4
    @Autowired
private KioskRepository kioskRepo;

public String changeKiosk(UUID vendorId, int kioskNumber) {
    Optional<Vendor> foundVendor = repo.findById(vendorId);
    if (foundVendor.isEmpty()) {
        return "The vendor with that id does not exist";
    }
    Optional<Kiosk> foundKiosk = kioskRepo.findByKioskNumber(kioskNumber);
    if (foundKiosk.isEmpty()) {
        return "The kiosk with that kioskNumber does not exist";
    }
    Vendor vendor = foundVendor.get();
    Kiosk kiosk = foundKiosk.get();
    if (repo.existsByKioskAndIdNot(kiosk, vendorId)) {
        return "That kiosk is taken by another vendor";
    }
    vendor.setKiosk(kiosk);
    repo.save(vendor);
    return "Kiosk changed successfully";
}

}

