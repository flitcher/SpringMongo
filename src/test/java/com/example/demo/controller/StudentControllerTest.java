package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private Student student = new Student("John", 60, 4.0);
    private Student student2 = new Student("Doe", 30, 2.0);
    @MockBean
    StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid/endpoint/test"))
                .andDo(print())
                .andExpect((status().isNotFound()));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student, student2));
        mockMvc.perform(get("/api/v1/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testAllStudentEmpty() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList());
        mockMvc.perform(get("/api/v1/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetStudent() throws Exception {
        when(studentService.getStudent(0)).thenReturn(Optional.ofNullable(student));
        mockMvc.perform(get("/api/v1/students/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void testCreateStudent() throws Exception {
        String studentAsJson = new ObjectMapper().writeValueAsString(student);

        mockMvc.perform(post("/api/v1/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(studentAsJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("Student John created with ID 0")));
    }
}

