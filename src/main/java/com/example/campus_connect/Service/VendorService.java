package com.example.campus_connect.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Model.Vendor;
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

}

