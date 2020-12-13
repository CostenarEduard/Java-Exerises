package com.example.demo.repository;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
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
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void createDepartmentAndEmployee(){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("test");
        DepartmentEntity departmentEntitySaved = departmentRepository.save(departmentEntity);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("test");
        employeeEntity.setJob("test");
        employeeEntity.setDepartmentEntity(departmentEntitySaved);
        employeeRepository.save(employeeEntity);
    }

    @Test
    public void saveEmployeeAndFindAllEmployees() {
        List<EmployeeEntity> employeeEntities = getEmployeeEntities();
        assertEquals(employeeEntities.size(), 1);
    }

    @Test
    public void findEmployeeById() {
        EmployeeEntity employeeEntity1 = getEmployeeEntity();
        assertEquals(employeeEntity1.getName(), "test");
    }

    @Test
    public void updateEmployeeById() {

        EmployeeEntity employeeEntity = getEmployeeEntity();
        employeeEntity.setName("test1");
        employeeRepository.save(employeeEntity);
        List<EmployeeEntity> employeeEntities = getEmployeeEntities();
        assertEquals(employeeEntities.get(0).getName(), "test1");
    }

    @Test
    public void deleteEmployeeById() {
        employeeRepository.deleteById(1L);
        List<EmployeeEntity> employeeEntities = getEmployeeEntities();
        assertEquals(employeeEntities.size(), 0);
    }

    private List<EmployeeEntity> getEmployeeEntities(){
        return StreamSupport.stream(employeeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }
    private EmployeeEntity getEmployeeEntity(){
        return employeeRepository.findById(1L).get();
    }
}