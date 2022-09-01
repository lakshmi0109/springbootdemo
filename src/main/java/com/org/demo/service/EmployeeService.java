package com.org.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.demo.EmployeeDTO.EmployeeDto;
import com.org.demo.model.Employee;
import com.org.demo.repository.EmployeeRepository;


 
@Service

public class EmployeeService {
     
    @Autowired
    EmployeeRepository employeeRepository;
    

        
    public List<Employee> getSortedEmployees() {
        List employees = new ArrayList();
      List<Employee> employeeAge = employeeRepository.findAll().stream().filter(e->e.getAge()<25).collect(Collectors.toList());
      
      
      // forEach(employee -> employees.add(employee));
      Collections.sort(employeeAge, Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
      
        return employeeAge;
      }


    public List<Employee> getAllEmployees() {
        List employees = new ArrayList();
        employeeRepository.findAll().forEach(employee -> employees.add(employee));
        return employees;
      }
      public Employee getEmployeeById(Long id) {
    	  
    	  EmployeeDto employeeDto = new EmployeeDto();
    	  
    	 return employeeRepository.findById(id).get();
    	}

      public void saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
      }

      public void delete(Long id) {
        employeeRepository.deleteById(id);
      }
      
     
}



