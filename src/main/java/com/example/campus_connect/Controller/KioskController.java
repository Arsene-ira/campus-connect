package com.example.campus_connect.Controller;

import com.example.campus_connect.Model.Kiosk;
import com.example.campus_connect.Service.KioskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/kiosks")
public class KioskController {
    @Autowired
    private KioskService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Kiosk kiosk) {
        return new ResponseEntity<>(service.save(kiosk), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Kiosk>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Kiosk> found = service.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The kiosk with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Kiosk kiosk) {
        String msg = service.update(id, kiosk);
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
    //c2
    @GetMapping("/free")
public ResponseEntity<List<Kiosk>> getFree() {
    return new ResponseEntity<>(service.getFree(), HttpStatus.OK);
}
}