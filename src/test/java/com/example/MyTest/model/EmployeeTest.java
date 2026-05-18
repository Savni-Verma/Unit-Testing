package com.example.MyTest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    Employee employee;

    @BeforeEach
    @Test
    void ObjectCreation(){
        employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    public void testEmployeeName(){
       employee.setName("Shavni");
       assertEquals("Shavni",employee.getName());
    }
    @Test
    public void testEmployeeSalary(){
        employee.setSalary(20.0000);
        assertEquals(20.0000,employee.getSalary());
    }
}
