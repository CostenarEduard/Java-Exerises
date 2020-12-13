package com.example.demo.repository;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.exception.EmployeeNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void saveDepartment() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("test");
        departmentRepository.save(departmentEntity);
    }

    @Test
    public void findAllEmployees() {
        List<DepartmentEntity> departmentEntities = getDepartmentEntities();
        assertEquals(departmentEntities.size(), 1);
    }

    @Test
    public void findEmployeeById() {
        DepartmentEntity departmentEntity = getDepartmentEntity();
        assertEquals(departmentEntity.getName(), "test");
    }

    @Test
    public void updateEmployeeById() {
        DepartmentEntity employeeEntity = getDepartmentEntity();
        employeeEntity.setName("test1");
        departmentRepository.save(employeeEntity);
        List<DepartmentEntity> departmentEntities = getDepartmentEntities();
        assertEquals(departmentEntities.get(0).getName(), "test1");
    }

    @Test
    public void deleteEmployeeById() {
        departmentRepository.deleteById(1L);

        List<DepartmentEntity> departmentEntities = getDepartmentEntities();
        assertEquals(departmentEntities.size(), 0);
    }

    private List<DepartmentEntity> getDepartmentEntities() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    private DepartmentEntity getDepartmentEntity() {
        return departmentRepository.findById(1L).get();
    }
}