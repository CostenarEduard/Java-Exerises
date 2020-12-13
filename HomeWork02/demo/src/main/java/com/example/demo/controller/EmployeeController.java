package com.example.demo.controller;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EmployeeController {
    private EmployeeService service;

    @PostMapping("/employee")
    ResponseEntity<EmployeeEntity> addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return service.addNewEmployee(employeeDto);
    }

    @GetMapping(value = "/employees", produces = APPLICATION_JSON_VALUE)
    List<EmployeeDto> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(value ="/employees/{id}", produces = APPLICATION_JSON_VALUE )
    EmployeeDto getEmployeeById(@PathVariable long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    EmployeeDto updateEmployeeById(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        return service.updateEmployeeById(id, employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable long id) {
        return service.deleteEmployeeById(id);
    }

    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }
}
