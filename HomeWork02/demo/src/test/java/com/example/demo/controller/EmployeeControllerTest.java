package com.example.demo.controller;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.EmployeeDto;
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
public class EmployeeControllerTest {
    private MockMvc mockMvc;
    private String requestJson;
    @InjectMocks
    private EmployeeController orderController;
    @Mock
    private EmployeeService service;
    @Mock
    private EmployeeDto employeeDto;

    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:dto/employee.json");
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        ObjectMapper mapper = new ObjectMapper();
        EmployeeDto orderRequestDto = mapper.readValue(file, EmployeeDto.class);
        requestJson = mapper.writeValueAsString(orderRequestDto);
    }

    @Test
    public void saveEmployee_expectSuccess() throws Exception {
        this.mockMvc
                .perform(post("/employee").contentType(APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllEmployees_expectSuccess() throws Exception {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(new EmployeeDto());
        when(service.getAllEmployees()).thenReturn(employeeDtoList);
        mockMvc.perform(get("/employees")
                    .contentType(APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getEmployeeById_expectException() throws Exception {
        when(service.getEmployeeById(4)).thenThrow(new EmployeeNotFoundException(4));
        mockMvc.perform(get("/employees" + "/4")
                    .contentType(APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateEmployee_expectSuccess() throws Exception {
        when(service.updateEmployeeById(4, employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(put("/employees" + "/4")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteEmployee_expectSuccess() throws Exception {
        mockMvc.perform(put("/employees" + "/4")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}