package com.example.payroll.config;

import com.example.payroll.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseConfigTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testBeanCreated() {
        assertNotNull(employeeRepository);
    }

}
