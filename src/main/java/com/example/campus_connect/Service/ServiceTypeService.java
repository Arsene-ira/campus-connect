package com.example.campus_connect.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Model.ServiceType;
import com.example.campus_connect.Repository.ServiceTypeRepository;


@Service 
public class ServiceTypeService {
    @Autowired 
    private ServiceTypeRepository repo;
    
    public String save(ServiceType type){
        repo.save(type);
        return "Service type saved successfully";
    }
    public List<ServiceType> getAll(){
        return repo.findAll();
    }
    
    public Optional<ServiceType> getById(UUID id){
            return repo.findById(id);
    }

    public String update(UUID id, ServiceType newData){
        Optional<ServiceType> found=repo.findById(id);
        if (found.isEmpty()){
            return "The service type with that id does not exist";
        }
        ServiceType old= found.get();
        old.setName(newData.getName());
        old.setVendors(newData.getVendors());
        repo.save(old);
        return "Service type Updated successfully";
    }
    public String delete(UUID id){
        if (!repo.existsById(id)){
            return "The service type with that id does not exist";
        }
        repo.deleteById(id);
        return "Service type deleted successfully";
    }
    //c3
    public List<ServiceType> getUnused() {
    return repo.findUnused();
}

}

