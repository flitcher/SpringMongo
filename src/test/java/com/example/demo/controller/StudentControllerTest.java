package com.example.demo.controller;

import com.example.demo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllStudents() throws Exception{
        RequestBuilder requestBuilder = get("/api/v1/students");
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void getStudent() {
    }

    @Test
    void testCreateStudent() throws Exception{
        Student student = new Student("John", 60, 3.6);
        String studentAsJson = new ObjectMapper().writeValueAsString(student);

        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentAsJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Student John created with ID 0"));
    }
}