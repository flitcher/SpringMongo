package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    public StudentRepository studentRepository;

    @GetMapping(value = "/api/v1/all")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(value = "/api/v1/search")
    public Optional<Student> getStudent(@RequestParam long id) {
        return studentRepository.findById(id);
    }

    @PostMapping(value = "/api/v1/create")
    public String createStudent(@RequestBody Student student) {
        Student newStudent = studentRepository.insert(student);
        return "Student created " + newStudent.getName();
    }

}
