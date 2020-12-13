package com.example.demo.service;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.model.EmployeeDto;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService service;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    public void addNewEmployee() {
        when(departmentRepository.findById(2L)).thenReturn(Optional.of(new DepartmentEntity(" ")));
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("test");
        employeeDto.setDepartmentId(2L);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("test");
        when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn((employeeEntity));
        ResponseEntity<EmployeeEntity> employeeEntity1 = service.addNewEmployee(employeeDto);
        assertEquals(employeeDto.getName(), employeeEntity1.getBody().getName());
    }

    @Test
    public void getEmployeeById() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(3L);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(3L);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(3);
        employeeEntity.setDepartmentEntity(departmentEntity);
        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employeeEntity));
        EmployeeDto employeeById = service.getEmployeeById(3L);
        assertEquals(employeeDto.getId(), employeeById.getId());
    }

    @Test
    public void getAllEmployees() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);
        departmentEntity.setName("test");

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setId(1L);
        employeeDto1.setName("test");
        employeeDto1.setJob("test");
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setId(1L);
        employeeDto2.setName("test");
        employeeDto2.setJob("test");

        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setId(1L);
        employeeEntity1.setName("test");
        employeeEntity1.setJob("test");
        employeeEntity1.setDepartmentEntity(departmentEntity);
        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setId(1L);
        employeeEntity2.setName("test");
        employeeEntity2.setJob("test");
        employeeEntity2.setDepartmentEntity(departmentEntity);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto1);
        employeeDtoList.add(employeeDto2);

        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        employeeEntityList.add(employeeEntity1);
        employeeEntityList.add(employeeEntity2);


        when(employeeRepository.findAll()).thenReturn(employeeEntityList);
        List<EmployeeDto> employeeDtoList2 = service.getAllEmployees();
        assertEquals(employeeDtoList2.get(1).getName(), employeeDtoList.get(1).getName());
    }

    @Test
    public void updateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("test");
        employeeDto.setId(3L);
        employeeDto.setDepartmentId(4L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("test");
        employeeEntity.setId(3L);
        employeeEntity.setDepartmentEntity(departmentEntity);

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employeeEntity));
        when(departmentRepository.findById(4L)).thenReturn(Optional.of(new DepartmentEntity("")));
        when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn((employeeEntity));

        EmployeeDto employeeDto2 = service.updateEmployeeById(3L, employeeDto);

        assertEquals(employeeDto.getName(), employeeDto2.getName());
    }

    @Test
    public void deleteEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(3L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(3L);

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employeeEntity));
        ResponseEntity<String> employeeDto1 = service.deleteEmployeeById(3L);

        assertTrue(employeeDto1.getBody().contains(employeeDto.getId().toString()));
    }

}