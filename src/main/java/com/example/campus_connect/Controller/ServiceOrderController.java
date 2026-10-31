package com.example.campus_connect.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.campus_connect.Model.OrderStatus;
import com.example.campus_connect.Model.ServiceOrder;
import com.example.campus_connect.Service.ServiceOrderService;

@RestController 
@RequestMapping ("/api/service-orders")
public class ServiceOrderController {
    @Autowired 
    private  ServiceOrderService service;

    @PostMapping("/save")
public ResponseEntity<String> save(@RequestBody ServiceOrder order) {
    String msg = service.save(order);
    if (msg.contains("in the past")) {
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
    if (msg.contains("already has")) {
        return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(msg, HttpStatus.CREATED);
}
    @GetMapping("/all")
    public ResponseEntity<List<ServiceOrder>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        Optional<ServiceOrder> found= service.getById(id);
        if (found.isPresent()){
            return new  ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>("The service order with that id does not exist", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ServiceOrder order){
        String msg= service.update(id, order);
        if(msg.contains("does not exist")){
            return  new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String msg = service.delete(id);
        if (msg.contains("does not exist")){
            return  new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }  
    //b2
    @GetMapping("/by-status")
    public ResponseEntity<List<ServiceOrder>> getByStatus(@RequestParam OrderStatus status){
        return new ResponseEntity<>(service.getByStatus(status), HttpStatus.OK);
    }
    //b3 
    @GetMapping("/between")
    public ResponseEntity<List<ServiceOrder>> getBetween(@RequestParam String start, @RequestParam String end ){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(service.getBetween(startDate, endDate), HttpStatus.OK);
    }
    //d2
    @PatchMapping("/{id}/status")
public ResponseEntity<String> changeStatus(@PathVariable UUID id,
                                           @RequestParam OrderStatus status) {
    String msg = service.changeStatus(id, status);
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
    if (msg.startsWith("Cannot change")) {
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(msg, HttpStatus.OK);
}
}
