package com.example.demo.controller;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.exception.EmployeeExistInDepartmentException;
import com.example.demo.model.DepartmentDto;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class DepartmentController {
    private DepartmentService service;

    @PostMapping("/employee/department")
    ResponseEntity<DepartmentEntity> addNewDepartment(@RequestBody DepartmentDto departmentDto) {
        return service.addNewDepartment(departmentDto);
    }

    @GetMapping(value = "/employees/departments", produces = APPLICATION_JSON_VALUE)
    List<DepartmentDto> getAllDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping(value ="/employees/departments/{id}", produces = APPLICATION_JSON_VALUE)
    DepartmentDto getDepartmentById(@PathVariable long id) {
        return service.getDepartmentById(id);
    }

    @PutMapping("/employees/departments/{id}")
    DepartmentDto updateDepartmentById(@PathVariable long id, @RequestBody DepartmentDto departmentDto) {
        return service.updateDepartmentById(id, departmentDto);
    }

    @DeleteMapping("/employees/departments/{id}")
    ResponseEntity<String> deleteDepartmentById(@PathVariable long id) throws EmployeeExistInDepartmentException {
        return service.deleteDepartmentById(id);
    }

    @Autowired
    public void setDepartment(DepartmentService service) {
        this.service = service;
    }
}
