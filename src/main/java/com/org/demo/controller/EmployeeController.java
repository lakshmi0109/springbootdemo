package com.org.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.org.demo.dto.EmployeeDto;
import com.org.demo.model.Employee;
import com.org.demo.service.EmployeeService;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;
 
    @GetMapping("/getSortedEmployees")
    private ResponseEntity<List<EmployeeDto>> getSortedEmployees() {
    	try {
    		 List<EmployeeDto> employeeDtos = employeeService.getSortedEmployees().stream()
    		          .map(this::convertToDto)
    		          .collect(Collectors.toList());
    		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
    		
    	}
    	catch(Exception ex) {
    	    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
      
    }

    @GetMapping("/getAllEmployees")
    private ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    	try {
    		 List<EmployeeDto> employeeDtos = employeeService.getAllEmployees().stream()
   		          .map(this::convertToDto)
   		          .collect(Collectors.toList());
   		return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatus.OK);
    		
    	}
    	catch(Exception ex) {
    	    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
      
    }
    @GetMapping("/getEmployee/{id}")
    private ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
    	
    	Employee emp = employeeService.getEmployeeById(id);
    	
    	EmployeeDto employeeDto = convertToDto(emp);
		if(null != emp) {
			return new ResponseEntity<EmployeeDto>(employeeDto,HttpStatus.OK);
		}
			return new ResponseEntity<EmployeeDto>(HttpStatus.NOT_FOUND);
	  
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
    
    private EmployeeDto convertToDto(Employee employee) {
  	  
  	  ModelMapper modelMapper = new ModelMapper();
  	  
  	  return modelMapper.map(employee, EmployeeDto.class);
    }
}