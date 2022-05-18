package com.rm.c.service;

import com.rm.c.dao.EmployeeDAO;
import com.rm.c.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    public void addEmployee(Employee employee){
        employeeDAO.save(employee);
    }
    
}
