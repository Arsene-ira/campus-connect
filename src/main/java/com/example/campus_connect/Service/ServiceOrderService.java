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
        //d111
        if (order.getOrderDate().isBefore(LocalDate.now())) {
        return "Service order date cannot be in the past";
    }
    if (repo.existsByVendorIdAndOrderDateAndStatusNot(
            order.getVendor().getId(), order.getOrderDate(), OrderStatus.DECLINED)) {
        return "Vendor already has an order on that date";
    }
    order.setStatus(OrderStatus.REQUESTED);
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
    //d2
    public String changeStatus(UUID id, OrderStatus newStatus) {
    Optional<ServiceOrder> found = repo.findById(id);
    if (found.isEmpty()) {
        return "The service order with that id does not exist";
    }
    ServiceOrder order = found.get();
    OrderStatus current = order.getStatus();

    boolean allowed =
        (current == OrderStatus.REQUESTED
            && (newStatus == OrderStatus.ACCEPTED || newStatus == OrderStatus.DECLINED))
        || (current == OrderStatus.ACCEPTED
            && (newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.DECLINED));

    if (!allowed) {
        return "Cannot change status from " + current + " to " + newStatus;
    }
    order.setStatus(newStatus);
    repo.save(order);
    return "Service order status updated successfully";
}
    

}
