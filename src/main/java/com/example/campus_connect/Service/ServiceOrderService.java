package com.example.campus_connect.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Model.OrderStatus;
import com.example.campus_connect.Model.ServiceOrder;
import com.example.campus_connect.Repository.ServiceOrderRepository;


@Service 
public class ServiceOrderService {
    @Autowired 
    private ServiceOrderRepository repo;
    
    public String save(ServiceOrder order){
        repo.save(order);
        return "Service order saved successfully";
    }
    public List<ServiceOrder> getAll(){
        return repo.findAll();
    }
    
    public Optional<ServiceOrder> getById(UUID id){
            return repo.findById(id);
    }

    public String update(UUID id, ServiceOrder newData){
        Optional<ServiceOrder> found=repo.findById(id);
        if (found.isEmpty()){
            return "The service order with that id does not exist";
        }
        ServiceOrder old= found.get();
        old.setOrderDate(newData.getOrderDate());
        old.setDescription(newData.getDescription());
        old.setPrice(newData.getPrice());
        old.setStudent(newData.getStudent());
        old.setVendor(newData.getVendor());
        repo.save(old);
        return "Service order Updated successfully";
    }
    public String delete(UUID id){
        if (!repo.existsById(id)){
            return "The service order with that id does not exist";
        }
        repo.deleteById(id);
        return "Service order deleted successfully";
    }
    //b2
    public List<ServiceOrder> getByStatus(OrderStatus status){
        return repo.findByStatusOrderByOrderDateAsc(status);
    }
    //b3
    public List<ServiceOrder> getBetween(LocalDate start, LocalDate end){
        return repo.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }

}
