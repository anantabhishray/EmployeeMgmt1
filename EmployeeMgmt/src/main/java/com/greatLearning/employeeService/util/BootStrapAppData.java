package com.greatLearning.employeeService.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.greatLearning.employeeService.dao.EmployeeRepository;
import com.greatLearning.employeeService.dao.RoleRepository;
import com.greatLearning.employeeService.dao.UserRepository;
import com.greatLearning.employeeService.entity.Employee;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor
public class BootStrapAppData {
//	@Autowired
//	private EmployeeRepository employeeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event) {
		
		
		User vinay = new User();
		vinay.setUsername("vinay");
		vinay.setPassword(this.passwordEncoder.encode("admin"));
		this.userRepository.save(vinay);
		
		
		Role userRole = new Role();
		userRole.setRoleName("ADMIN");
		userRole.setUser(vinay);
		this.roleRepository.save(userRole);
		
		
	}	

}

