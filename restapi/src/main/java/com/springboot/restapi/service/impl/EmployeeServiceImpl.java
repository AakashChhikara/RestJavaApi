package com.springboot.restapi.service.impl;

import java.util.List;

import com.springboot.restapi.exception.ResourceNotFoundException;
import com.springboot.restapi.model.Employee;
import com.springboot.restapi.repository.EmployeeRepository;
import com.springboot.restapi.service.EmployeeService;

import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        //Both ways works

        // Optional<Employee> employee = _employeeRepository.findById(id);
        // if(employee.isPresent()){
        //     return employee.get();
        // }else{
        //     throw new ResourceNotFoundException("Employee", "Id", id);
        // }

        return employeeRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());  
        //save to db
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Employee", "Id", id));

        employeeRepository.deleteById(id);
    }

    
    

}
