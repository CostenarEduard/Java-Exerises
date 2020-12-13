package com.example.demo.service;


import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.model.DepartmentDto;
import com.example.demo.model.EmployeeDto;
import com.example.demo.repository.DepartmentRepository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService service;
    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    public void addNewDepartment() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(4L);
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);
        when(departmentRepository.save(Mockito.any(DepartmentEntity.class))).thenReturn((departmentEntity));
        ResponseEntity<DepartmentEntity> departmentEntity1 = service.addNewDepartment(departmentDto);
        assertEquals(departmentDto.getName(), departmentEntity1.getBody().getName());
    }

    @Test
    public void getDepartmentById() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(4L);
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);
        when(departmentRepository.findById(4L)).thenReturn(Optional.of(departmentEntity));
        DepartmentDto departmentDto1 = service.getDepartmentById(4L);
        assertEquals(departmentDto.getId(), departmentDto1.getId());
    }

    @Test
    public void getAllDepartments() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(4L);
        DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto.setId(5L);
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);
        DepartmentEntity departmentEntity1 = new DepartmentEntity();
        departmentEntity.setId(4L);

        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto);
        departmentDtoList.add(departmentDto1);

        List<DepartmentEntity> departmentEntityList = new ArrayList<>();
        departmentEntityList.add(departmentEntity);
        departmentEntityList.add(departmentEntity1);

        when(departmentRepository.findAll()).thenReturn(departmentEntityList);
        List<DepartmentDto> departmentDtoList1 = service.getAllDepartments();
        assertEquals(departmentDtoList1.get(1).getName(), departmentDtoList.get(1).getName());
    }

    @Test
    public void updateEmployee() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(4L);
        departmentDto.setName("test");

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);
        departmentEntity.setName("test");

        when(departmentRepository.findById(4L)).thenReturn(Optional.of(departmentEntity));
        when(departmentRepository.save(Mockito.any(DepartmentEntity.class))).thenReturn((departmentEntity));

        DepartmentDto departmentDto1 = service.updateDepartmentById(4L, departmentDto);

        assertEquals(departmentDto.getName(), departmentDto1.getName());
    }

    @Test
    public void deleteEmployee() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(4L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(4L);

        when(departmentRepository.findById(4L)).thenReturn(Optional.of(departmentEntity));
        ResponseEntity<String> departmentDto1 = service.deleteDepartmentById(4L);

        assertTrue(departmentDto1.getBody().contains(departmentDto.getId().toString()));
    }

}