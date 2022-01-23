package com.springboot.restapi.repository;

import com.springboot.restapi.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    
}
 