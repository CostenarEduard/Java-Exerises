package com.example.demo.repository;

import com.example.demo.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    Optional<String> findByName(String name);
}
