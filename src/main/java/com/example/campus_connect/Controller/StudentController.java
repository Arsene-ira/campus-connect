package com.example.campus_connect.Controller;

import com.example.campus_connect.Model.Student;
import com.example.campus_connect.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Student student) {
        return new ResponseEntity<>(service.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Student> found = service.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The student with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Student student) {
        String msg = service.update(id, student);
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
    //b1
    @GetMapping("/by-lastname")
    public ResponseEntity<List<Student>> getByLastName(@RequestParam String lastName){
        return new ResponseEntity<>(service.getByLastName(lastName), HttpStatus.OK);
    }
}