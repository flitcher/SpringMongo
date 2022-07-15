package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid/endpoint/test"))
                .andExpect((status().isNotFound()));
    }

    @Test
    void testGetAllStudents() throws Exception{
        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateStudent() throws Exception{
        Student student = new Student("John", 60, 3.6);
        String studentAsJson = new ObjectMapper().writeValueAsString(student);

        mockMvc.perform(post("/api/v1/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(studentAsJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("Student John created with ID 0")));
    }
}

