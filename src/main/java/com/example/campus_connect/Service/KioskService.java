package com.example.campus_connect.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Model.Kiosk;
import com.example.campus_connect.Repository.KioskRepository;


@Service 
public class KioskService {
    @Autowired 
    private KioskRepository repo;
    
    public String save(Kiosk kiosk){
        repo.save(kiosk);
        return "Service kiosk saved successfully";
    }
    public List<Kiosk> getAll(){
        return repo.findAll();
    }
    
    public Optional<Kiosk> getById(UUID id){
            return repo.findById(id);
    }

    public String update(UUID id, Kiosk newData){
        Optional<Kiosk> found=repo.findById(id);
        if (found.isEmpty()){
            return "The kiosk with that id does not exist";
        }
        Kiosk old= found.get();
        old.setName(newData.getName());
        old.setKioskNumber(newData.getKioskNumber());
        
        repo.save(old);
        return "Kiosk Updated successfully";
    }
    public String delete(UUID id){
        if (!repo.existsById(id)){
            return "The Kiosk with that id does not exist";
        }
        repo.deleteById(id);
        return "Kiosk deleted successfully";
    }
    //c2
    public List<Kiosk> getFree() {
    return repo.findFreeKiosks();
    }

}

