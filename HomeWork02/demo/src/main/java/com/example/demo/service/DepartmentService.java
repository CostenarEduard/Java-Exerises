package com.example.demo.service;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.exception.DepartmentAlreadyExistsException;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.model.DepartmentDto;
import com.example.demo.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public ResponseEntity<DepartmentEntity> addNewDepartment(DepartmentDto departmentDto){
        if (departmentRepository.findByName(departmentDto.getName()).isPresent()) {
            throw new DepartmentAlreadyExistsException("Department already exists.");
        }
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName(departmentDto.getName());
        departmentRepository.save(departmentEntity);
        return new ResponseEntity<> (departmentEntity, HttpStatus.OK);
    }

    public List<DepartmentDto> getAllDepartments() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(),false)
                .map(this::returnDepartmentDto).collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(long id) {
        DepartmentEntity departmentEntity = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        return returnDepartmentDto(departmentEntity);
    }

    public DepartmentDto updateDepartmentById(long id, DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentDto.setId(departmentEntity.getId());
        departmentEntity.setName(departmentDto.getName());
        departmentRepository.save(departmentEntity);
        return departmentDto;
    }

    public ResponseEntity<String> deleteDepartmentById(Long id) {
        departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentRepository.deleteById(id);
        return new ResponseEntity<> ("Department with id: " + id + " has been successfully deleted.", HttpStatus.OK);
    }

    private DepartmentDto returnDepartmentDto(DepartmentEntity departmentEntity) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(departmentEntity.getId());
        departmentDto.setName(departmentEntity.getName());
        return departmentDto;
    }
}
