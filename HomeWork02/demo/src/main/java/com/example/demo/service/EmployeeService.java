package com.example.demo.service;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.EmployeeDto;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public ResponseEntity<EmployeeEntity> addNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setJob(employeeDto.getJob());
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(employeeDto.getDepartmentId());
        employeeEntity.setDepartmentEntity(departmentEntityOptional.orElseThrow(() -> new DepartmentNotFoundException(employeeDto.getId())));
        employeeRepository.save(employeeEntity);
        return new ResponseEntity<>(employeeEntity, HttpStatus.OK);
    }

    public List<EmployeeDto> getAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .map(this::returnEmployeeDto).collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(long id) {
        EmployeeEntity employeeEntity = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return returnEmployeeDto(employeeEntity);
    }

    public EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeDto.setId(employeeEntity.getId());
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setJob(employeeDto.getJob());
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(employeeDto.getDepartmentId());
        employeeEntity.setDepartmentEntity(departmentEntityOptional.orElseThrow(() -> new DepartmentNotFoundException(employeeDto.getDepartmentId())));
        employeeRepository.save(employeeEntity);
        return employeeDto;
    }

    public ResponseEntity<String> deleteEmployeeById(long id) {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("Employee with id: " + id + " has been successfully deleted.", HttpStatus.OK);
    }

    private EmployeeDto returnEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeEntity.getId());
        employeeDto.setName(employeeEntity.getName());
        employeeDto.setJob(employeeEntity.getJob());
        employeeDto.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        employeeDto.setDepartmentName(employeeEntity.getDepartmentEntity().getName());
        return employeeDto;
    }
}
