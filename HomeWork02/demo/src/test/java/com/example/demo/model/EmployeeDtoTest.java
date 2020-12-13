package com.example.demo.model;

import com.example.demo.TransferObjectTestUnits;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class EmployeeDtoTest {

    @Test
    public void serialization() throws IOException {
        TransferObjectTestUnits.assertSerialization("employee.json", EmployeeDto.class);
    }
}