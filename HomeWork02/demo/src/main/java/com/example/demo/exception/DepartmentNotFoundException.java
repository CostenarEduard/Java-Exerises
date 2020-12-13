package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(long departmentId) {
        super("Could not find department id: " + departmentId);
    }
}
