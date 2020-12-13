package com.example.demo.entities;

import com.example.demo.exception.EmployeeExistInDepartmentException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@NoArgsConstructor
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_DEPARTMENT")
    @SequenceGenerator(name = "S_DEPARTMENT", sequenceName = "S_DEPARTMENT", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL) //fetch = FetchType.LAZY
    private List<EmployeeEntity> employeeEntityList;

    @PreRemove
    public void checkReviewAssociationBeforeRemoval() {
        if(!this.employeeEntityList.isEmpty()) {
            throw new EmployeeExistInDepartmentException("Can't remove department because there is an employee using it.");
        }
    }

    public DepartmentEntity(String name) {
        this.name = name;
    }
}
