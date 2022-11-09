package com.greatLearning.employeeService.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatLearning.employeeService.entity.Employee;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;
//import com.greatLearning.employeeService.entity.Role;
//import com.greatLearning.employeeService.entity.User;
import com.greatLearning.employeeService.service.EmployeeService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@PostMapping("/user")
	public User addUser(@RequestBody User theUser) {
		return employeeService.save(theUser);
	}

	@PostMapping("/role")
	public Role addRole(@RequestBody Role theRole) {
		return employeeService.save(theRole);
	}


	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return theEmployee;
	}


	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

		return theEmployee;
	}

	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee theEmployee) {

		return employeeService.update(employeeId,theEmployee);
	}

	@DeleteMapping("/employees/{employeeId}")

	public String deleteEmployee(@PathVariable int employeeId) {

		Employee tempEmployee = employeeService.findById(employeeId);

		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	@GetMapping("/employees/sortAsc")
	public List<Employee> sortByFirstNameAsc() {
		return employeeService.sortByFirstNameAsc();
	}

	@GetMapping("/employees/sortDesc")
	public List<Employee> sortByFirstNameDesc() {
		return employeeService.sortByFirstNameDesc();
	}


}
