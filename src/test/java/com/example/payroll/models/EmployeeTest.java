package com.example.payroll.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeTest {

    @Test
    public void testEntity() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setRole("Engineer");
        employee.setSalary(100500.00);

        assertEquals(employee.getId(), 1L);
        assertEquals(employee.getName(), "John Doe");
        assertEquals(employee.getFirstName(), "John");
        assertEquals(employee.getLastName(), "Doe");
        assertEquals(employee.getRole(),"Engineer");
        assertEquals(employee.getSalary(), 100500.00);

    }
}
