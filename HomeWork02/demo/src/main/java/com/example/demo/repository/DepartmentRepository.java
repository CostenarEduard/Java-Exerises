package com.example.demo.repository;

import com.example.demo.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
    Optional<String> findByName(String name);
}
