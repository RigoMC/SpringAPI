package com.rm.c.dao;

import com.rm.c.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee, Long>{
    
}
