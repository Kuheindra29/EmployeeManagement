package com.kuheindra.employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuheindra.employees.dto.EmployeeResponse;
import com.kuheindra.employees.service.EmployeeService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmployeeService employeeService;

    @Test
    void getEmployeeShouldReturn200()
            throws Exception {

        EmployeeResponse response =
                new EmployeeResponse(
                        1L,
                        "John",
                        "Smith",
                        "john@gmail.com",
                        "IT");

        when(employeeService.getEmployee(1L))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.firstName")
                                .value("John"));
    }
}