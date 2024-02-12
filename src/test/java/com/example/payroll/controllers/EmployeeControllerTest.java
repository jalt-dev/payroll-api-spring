package com.example.payroll.controllers;

import com.example.payroll.models.Employee;
import com.example.payroll.repositories.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;


//Note: The data being testing was preloaded in com.example.payroll.repositories.EmployeeRepository

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllEmployees() throws Exception {
        int size = repository.findAll().size();

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.employeeList", hasSize(size)))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/employees"));
    }

    @Test
    public void testGetOneEmployee() throws Exception {
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.role").value("CTO"))
                .andExpect(jsonPath("$.salary").value(125000.00))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/employees/1"))
                .andExpect(jsonPath("$._links.employees.href").value("http://localhost/employees"));
    }

    @Test
    public void testEmployeeNotFound() throws Exception {
        mockMvc.perform(get("/employees/55"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could not find employee 55"));
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employeeDetails = new Employee("Jane", "Smith", "COO", 200000.00);
        String jsonRequest = objectMapper.writeValueAsString(employeeDetails);

        ResultActions resultActions = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        );

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employeeDetails.getName()))
                .andExpect(jsonPath("$.firstName").value(employeeDetails.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(employeeDetails.getLastName()))
                .andExpect(jsonPath("$.role").value(employeeDetails.getRole()))
                .andExpect(jsonPath("$.salary").value(employeeDetails.getSalary()))
                .andExpect(jsonPath("$._links.self.href", Matchers.notNullValue()))
                .andExpect(jsonPath("$._links.employees.href", Matchers.notNullValue()));

    }

    @Test
    public void testEditEmployee() throws Exception {
        Employee employeeDetails = new Employee("Jane", "Smith", "COO", 489999.00);
        repository.save(employeeDetails);

        double newSalary = 500000.00;

        employeeDetails.setSalary(newSalary);

        String jsonRequest = objectMapper.writeValueAsString(employeeDetails);

        ResultActions resultActions = mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        );

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employeeDetails.getName()))
                .andExpect(jsonPath("$.firstName").value(employeeDetails.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(employeeDetails.getLastName()))
                .andExpect(jsonPath("$.role").value(employeeDetails.getRole()))
                .andExpect(jsonPath("$.salary").value(employeeDetails.getSalary()));

    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/employees/1")).andExpect(status().isNoContent());
        assertNull(repository.findById(1L).orElse(null));
    }
}
