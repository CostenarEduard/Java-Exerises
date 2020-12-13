package com.example.demo.controller;

import com.example.demo.model.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
public class EmployeeControllerV2 {
    private EmployeeService service;

    @PostMapping(value = "/employeeV2", consumes = APPLICATION_XML_VALUE)
    void addNewEmployee(@RequestBody EmployeeDto employeeDto){
        service.addNewEmployee(employeeDto);
    }

    @GetMapping(value = "/employeesV2", produces = APPLICATION_XML_VALUE)
    List<EmployeeDto> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(value = "/employeesV2/{id}", produces = APPLICATION_XML_VALUE)
    EmployeeDto getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @PutMapping(value = "/employeesV2/{id}", consumes = APPLICATION_XML_VALUE, produces = APPLICATION_XML_VALUE)
    EmployeeDto updateEmployeeById(@PathVariable int id, @RequestBody EmployeeDto employeeDto) { return service.updateEmployeeById(id, employeeDto); }

    @DeleteMapping("/employeesV2/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        return service.deleteEmployeeById(id);
    }

    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }
}
