package com.example.campus_connect.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.campus_connect.Model.Student;
import com.example.campus_connect.Repository.StudentRepository;


@Service 
public class StudentService {
    @Autowired 
    private StudentRepository repo;
    
    public String save(Student order){
        repo.save(order);
        return "Student saved successfully";
    }
    public List<Student> getAll(){
        return repo.findAll();
    }
    
    public Optional<Student> getById(UUID id){
            return repo.findById(id);
    }

    public String update(UUID id, Student newData){
        Optional<Student> found=repo.findById(id);
        if (found.isEmpty()){
            return "The student with that id does not exist";
        }
        Student old= found.get();
        old.setFirstName(newData.getFirstName());
        old.setLastName(newData.getLastName());
        old.setStudentNumber(newData.getStudentNumber());
        old.setEmail(newData.getEmail());
        old.setPhone(newData.getPhone());
        repo.save(old);
        return "Student  Updated successfully";
    }
    public String delete(UUID id){
        if (!repo.existsById(id)){
            return "The student with that id does not exist";
        }
        repo.deleteById(id);
        return "Student deleted successfully";
    }
    //b1
    public List<Student> getByLastName(String lastName){
        return repo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

}

