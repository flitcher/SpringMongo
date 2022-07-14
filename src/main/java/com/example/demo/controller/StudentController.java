package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/api/v1/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/api/v1/students/{id}")
    public Optional<Student> getStudent(@PathVariable("id") long id) {
        return studentService.getStudent(id);
    }

    @PostMapping(value = "/api/v1/students")
    public String createStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student " + student.getName() + " created with ID " + student.getId();
    }

}
