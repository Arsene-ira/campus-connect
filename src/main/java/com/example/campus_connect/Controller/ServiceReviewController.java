package com.example.campus_connect.Controller;

import com.example.campus_connect.Model.ServiceReview;
import com.example.campus_connect.Service.ServiceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-reviews")
public class ServiceReviewController {
    @Autowired
    private ServiceReviewService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID serviceOrderId,
             @RequestBody ServiceReview review) {
        String msg = service.save(serviceOrderId, review);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        if (msg.contains("only for")) {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceReview>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/student/{studentNumber}")
    public ResponseEntity<List<ServiceReview>> getByStudent(@PathVariable String studentNumber) {
        return new ResponseEntity<>(service.getByStudent(studentNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = service.delete(id);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}