package com.greatLearning.employeeService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatLearning.employeeService.dao.EmployeeRepository;
import com.greatLearning.employeeService.dao.RoleRepository;
import com.greatLearning.employeeService.dao.UserRepository;

//import com.greatLearning.employeeService.dao.RoleRepository;
//import com.greatLearning.employeeService.dao.UserRepository;
import com.greatLearning.employeeService.entity.Employee;
//import com.greatLearning.employeeService.entity.Role;
//import com.greatLearning.employeeService.entity.User;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;

@SuppressWarnings("unused")
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	public User save(User theUser) {
		return userRepository.save(theUser);
	}

	@Override
	public Role save(Role theRole) {
		return roleRepository.save(theRole);
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public Employee update(int employeeId,Employee theEmployee) {
		Optional<Employee> Optionalemp = employeeRepository.findById(employeeId);
		Employee emp = null;

		if (Optionalemp.isPresent()) {

			emp = Optionalemp.get();
			emp.setFirstName(theEmployee.getFirstName());
			emp.setLastName(theEmployee.getLastName());
			emp.setEmail(theEmployee.getEmail());

			return employeeRepository.save(emp);

		} 

		else {
			throw new RuntimeException("Did not find employee id - " + employeeId);
		}		
	}

	@Override
	public List<Employee> sortByFirstNameDesc() {
		return employeeRepository.findAllByOrderByFirstNameDesc();
	}

}
