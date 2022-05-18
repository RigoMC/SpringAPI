package com.rm.c.service;

import com.rm.c.dao.ContractDAO;
import com.rm.c.dao.EmployeeDAO;
import com.rm.c.domain.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    
    @Autowired
    private ContractDAO contractDAO;
    
    public void addContract(Contract contract){
        contractDAO.save(contract);
    }
    
}
