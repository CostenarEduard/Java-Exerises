package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name= "employee")
public class EmployeeDto {
    private Long id;
    private String name;
    private String job;
    private long departmentId;
    private String departmentName;
}
