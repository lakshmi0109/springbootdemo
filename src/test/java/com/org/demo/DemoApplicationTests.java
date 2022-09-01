package com.org.demo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.demo.model.Employee;
import com.org.demo.repository.EmployeeRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void contextLoads() {
	}
	@Test
	@Order(1)
	public void saveEmployeeTest() {
		
		Employee emp = new Employee();
		
		emp.setAge(2);
		emp.setEmail("lakshmi.rongali@email.com");
		emp.setFirstName("Laxmi");
		emp.setLastName("Rongali");
		emp.setId(1L);
		
		employeeRepository.save(emp);
		assertNotNull(employeeRepository.findById(1L).get());
		
	}
	
	@Test
	@Order(2)
	
	public void findByIDTest() {
		
		Employee emp = employeeRepository.findById(1L).get();
		assertEquals("Laxmi", emp.getFirstName());
	}
	@Test
	@Order(3)
	
	public void findAllTest()
	{
		List<Employee> list = employeeRepository.findAll();
		
		assertThat(list).size().isGreaterThan(0);
	}
}