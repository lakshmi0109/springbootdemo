package com.org.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.org.demo.model.Employee;
import com.org.demo.repository.EmployeeRepository;
 
@Service

public class EmployeeService {
     
    @Autowired
    EmployeeRepository employeeRepository;
    
    public List<Employee> getAllEmployees() {
        List employees = new ArrayList();
        employeeRepository.findAll().forEach(employee -> employees.add(employee));
        return employees;
      }

      public Employee getEmployeeById(Long id) {
    	 return employeeRepository.findById(id).get();
    	}

      public void saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
      }

      public void delete(Long id) {
        employeeRepository.deleteById(id);
      }
}
