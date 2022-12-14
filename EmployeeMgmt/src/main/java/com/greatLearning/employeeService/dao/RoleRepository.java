package com.greatLearning.employeeService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatLearning.employeeService.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
