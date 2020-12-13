package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMPLOYEE")
    @SequenceGenerator(name = "S_EMPLOYEE", sequenceName = "S_EMPLOYEE", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "JOB")
    private String job;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private DepartmentEntity departmentEntity;
}
