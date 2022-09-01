package com.org.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.demo.model.Employee;
import com.org.demo.service.EmployeeService;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;
 
    @GetMapping("/getAllEmployees")
    private ResponseEntity<List<Employee>> getAllEmployees() {
    	try {
    		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    		
    	}
    	catch(Exception ex) {
    	    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
      
    }

    @GetMapping("/getEmployee/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
    	
    	Employee emp = employeeService.getEmployeeById(id);
		if(null != emp) {
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	  
    }

    @PostMapping("/saveEmployee")
    private ResponseEntity createEmployee(@RequestBody Employee employee) {
      try{
        employeeService.saveOrUpdate(employee);
      } catch (Exception exception) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity("New employee created with id: "+employee.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    private ResponseEntity deleteById(@PathVariable("id") Long id) {
      try{
        employeeService.delete(id);
      } catch (Exception exception) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      return new ResponseEntity("Employee deleted with id: "+id, HttpStatus.OK);
    }
}