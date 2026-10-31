package com.example.campus_connect.Controller;

import com.example.campus_connect.Model.Vendor;
import com.example.campus_connect.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Vendor vendor) {
        return new ResponseEntity<>(service.save(vendor), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vendor>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Vendor> found = service.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The vendor with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Vendor vendor) {
        String msg = service.update(id, vendor);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = service.delete(id);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    //c1
    @GetMapping("/by-service-type")
public ResponseEntity<List<Vendor>> getByServiceType(@RequestParam String name) {
    return new ResponseEntity<>(service.getByServiceType(name), HttpStatus.OK);
}
    //d3
    @PutMapping("/{vendorId}/service-types/{serviceTypeId}")
    public ResponseEntity<String> addServiceType(@PathVariable UUID vendorId,
    @PathVariable UUID serviceTypeId) {
    String msg = service.addServiceType(vendorId, serviceTypeId);
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
    if (msg.contains("already has")) {
        return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(msg, HttpStatus.OK);
}
    //dew4
    @PatchMapping("/{vendorId}/kiosk")
public ResponseEntity<String> changeKiosk(@PathVariable UUID vendorId,
      @RequestParam int kioskNumber) {
    String msg = service.changeKiosk(vendorId, kioskNumber);
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
    if (msg.contains("taken")) {
        return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(msg, HttpStatus.OK);
}
}