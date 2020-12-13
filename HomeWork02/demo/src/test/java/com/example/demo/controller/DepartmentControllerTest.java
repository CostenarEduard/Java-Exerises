package com.example.demo.controller;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.DepartmentDto;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DepartmentControllerTest {
    private MockMvc mockMvc;
    private String requestJson;
    @InjectMocks
    private DepartmentController orderController;
    @Mock
    private DepartmentService service;
    @Mock
    private DepartmentDto departmentDto;

    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:dto/department.json");
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        ObjectMapper mapper = new ObjectMapper();
        DepartmentDto orderRequestDto = mapper.readValue(file, DepartmentDto.class);
        requestJson = mapper.writeValueAsString(orderRequestDto);
    }

    @Test
    public void saveDepartment_expectSuccess() throws Exception {
        this.mockMvc
                .perform(post("/employee" + "/department").contentType(APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllDepartment_expectSuccess() throws Exception {
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(new DepartmentDto());
        when(service.getAllDepartments()).thenReturn(departmentDtoList);
        mockMvc.perform(get("/employees" + "/departments")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getDepartmentById_expectException() throws Exception {
        when(service.getDepartmentById(1)).thenThrow(new EmployeeNotFoundException(1));
        mockMvc.perform(get("/employees" + "/departments" + "/1")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateDepartment_expectSuccess() throws Exception {
        when(service.updateDepartmentById(1, departmentDto)).thenReturn(departmentDto);
        mockMvc.perform(put("/employees" + "/departments" + "/1")
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteDepartment_expectSuccess() throws Exception {
        mockMvc.perform(put("/employees" + "/departments" + "/1")
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}